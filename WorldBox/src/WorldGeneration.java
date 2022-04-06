import java.awt.Color;
import java.util.Random;
import java.awt.Image;
import java.awt.image.BufferedImage;

public class WorldGeneration 
{
	public static boolean useRandomColors = false;	
	public static boolean useRandomHeights = false;	
	public static long worldSeed = 0;
	
	//---- COLOR SETTINGS ----
	
	//---- GRASS COLOR ----
	public static Color grassColor = new Color(0, 180, 0);
	public static Color savanaGrassColor = new Color(140, 255, 0);
	public static Color forestGrassColor = new Color(32, 150, 0);
	public static Color greenForestGrassColor = new Color(0, 255, 132);
	
	public static Color sandColor = new Color(255, 225, 0);
	public static Color iceColor = new Color(0, 255, 255);
	
	public static Color snowColor = new Color(255, 255, 255);
	public static Color stoneColor = new Color(120, 120, 120);
	public static Color seaColor = new Color(0, 128, 255);
	
	public static float seaLevel = .0f;
	public static float sandReach = .05f;
	public static float snowLevel = .6f;
	public static float stoneLevel = .4f;
	
	//---- Coloring ----
	public static float tileMinBrightness = .8f;	
	public static float waterFadeoutMin = .8f;
	
	public static Random rn = new Random();
	public static Image worldImage;
	public static OpenSimplexNoise noise = new OpenSimplexNoise();	
	
	public static void GenerateWorld() 
	{
		Main.grid = new Tile[Main.simulationSize][Main.simulationSize];
		BufferedImage wMap = new BufferedImage(Main.simulationSize, Main.simulationSize,BufferedImage.TYPE_INT_RGB);
				
		//---- SEED GENERATING ----
		if(worldSeed != 0) 
		{
			rn.setSeed(worldSeed);
			noise = new OpenSimplexNoise(worldSeed);
		}
		else 
		{
			worldSeed = System.currentTimeMillis();
			rn.setSeed(worldSeed);
			noise = new OpenSimplexNoise(worldSeed);
		}
		
		//---- Random Colors Generation ----
		if(useRandomColors) 
		{
			grassColor = RandomizedColor();
			savanaGrassColor = RandomizedColor();
			forestGrassColor = RandomizedColor();
			
			snowColor = RandomizedColor();
			sandColor = RandomizedColor();
			seaColor = RandomizedColor();
			stoneColor = RandomizedColor();
		}
		
		if(useRandomHeights) 
		{
			seaLevel = -1 + rn.nextFloat() * (1 - -1);	
			sandReach = -1 + rn.nextFloat() * (1 - -1);	
			snowLevel = -1 + rn.nextFloat() * (1 - -1);	
			stoneLevel = -1 + rn.nextFloat() * (1 - -1);	
		}
		
		for(int x = 0; x < Main.simulationSize; x++) 
		{
			for(int y = 0; y < Main.simulationSize; y++) 
			{
				Tile t = new Tile();
				
				//---- SHAPE GENERATION ----
				double noise1 = noise.eval((double)x / (double)24, (double)y / (double)24, 1);
				double noise2 = noise.eval((double)x / (double)8, (double)y / (double)8, .5f);	
				double combined1 = Lerp(noise1, noise2, .5d);
				
				double noise3 = noise.eval((double)x / (double)16, (double)y / (double)16, 1);	
				double combined2 = Lerp(combined1, noise3, .5d);
				
				double noise4 = noise.eval((double)x / (double)64, (double)y / (double)64, 0);
				double combined3 = Clamp(combined2 + noise4, -1, 1);	
				
				double noise5 = noise.eval((double)x / (double)128, (double)y / (double)24, 0);
				double combined4 = Lerp(noise5, combined3, .8d);
				
				t.setHeight(combined4);
				
				//---- BIOME GENERATION -----
				double dessertBiome = noise.eval((double)x / (double)64, (double)y / (double)64, .5);
				dessertBiome = Lerp(dessertBiome, combined4, .2d);
				dessertBiome = dessertBiome > .5 ? 1 : 0;
				
				double snowBiome = noise.eval((double)x / (double)64 + 10, (double)y / (double)64 + 10, .5);
				snowBiome = Lerp(snowBiome, combined4, .2d);
				snowBiome = snowBiome > .5 ? 1 : 0;
				
				double savanaBiome = noise.eval((double)x / (double)64 + 20, (double)y / (double)64 + 20, .5);
				savanaBiome = Lerp(savanaBiome, combined4, .2d);
				savanaBiome = savanaBiome > .5 ? 1 : 0;
				
				double forestBiome = noise.eval((double)x / (double)48 + 20, (double)y / (double)48 + 20, 1);
				forestBiome = Lerp(forestBiome, combined4, .2d);
				forestBiome = forestBiome > .4 ? 1 : 0;
				
				double greenForestBiome = noise.eval((double)x / (double)64 + 30, (double)y / (double)64 + 30, 1);
				greenForestBiome = Lerp(greenForestBiome, combined4, .2d);
				greenForestBiome = greenForestBiome > .5 ? 1 : 0;
				
				float brightness = 1;
				Color tileColor = seaColor;
				
				//---- WATER ----
				if(t.getHeight() < seaLevel) 
				{					
					brightness = WaterClamp((float)(1 - (t.getHeight() * -1)) - seaLevel, waterFadeoutMin, 1f);					
					tileColor = seaColor;			
					
					t.setType(TileType.Water);	
					t.setBiome(BiomeType.Sea);
					
					//---- ICE/FROZE OVERRIDE ----
					if(snowBiome == 1) 
					{					
						t.setType(TileType.Ice);
						t.setBiome(BiomeType.FrozenSea);
						tileColor = iceColor;
						
						//Custom ICE brightness					
						float iceBrightness = tileMinBrightness + rn.nextFloat() * (1 - tileMinBrightness);				
						brightness *= iceBrightness;
					}
				}
				else 
				{
					brightness = tileMinBrightness + rn.nextFloat() * (1 - tileMinBrightness);	
					
					tileColor = grassColor;
					t.setType(TileType.Grass);
					t.setBiome(BiomeType.Grassland);
					
					//---- SAVANAS ----
					if(savanaBiome == 1) 
					{				
						t.setType(TileType.Grass);
						t.setBiome(BiomeType.Savana);
						tileColor = savanaGrassColor;
					}
					
					if(greenForestBiome == 1) 
					{
						t.setType(TileType.Grass);
						t.setBiome(BiomeType.GreenForest);
						tileColor = greenForestGrassColor;
					}
					
					//---- FOREST ----
					if(forestBiome == 1) 
					{
						t.setType(TileType.Grass);
						t.setBiome(BiomeType.Forest);
						tileColor = forestGrassColor;
					}
					
					

					//---- BEACHES ----
					if( t.getHeight() - seaLevel < sandReach ) 
					{									
						t.setType(TileType.Sand);		
						tileColor = sandColor;		
						t.setBiome(BiomeType.Beach);
					}
					
					//---- FROZEN OVERRIDE ----
					if(snowBiome == 1) 
					{
						t.setType(TileType.Snow);
						t.setBiome(BiomeType.Frozen);
						tileColor = snowColor;	
					}
					
					//---- DESSERT OVERRIDE ----
					if(dessertBiome == 1) 
					{
						t.setType(TileType.Sand);
						t.setBiome(BiomeType.Dessert);
						tileColor = sandColor;	
					}
					
					//---- STONE ----
					if(t.getHeight() >= stoneLevel) 
					{		
						t.setType(TileType.Stone);					
						tileColor = stoneColor;
					}
					
					//---- SNOW ----
					if(t.getHeight() >= snowLevel) 
					{				
						t.setType(TileType.Snow);						
						tileColor = snowColor;
				
					}										
				}				
				
				//Handles brightness of tiles		
				tileColor = GetColor(tileColor, brightness);
				
				//Adds tile to buffered image
				wMap.setRGB(x, y, tileColor.getRGB());
				worldImage = wMap;
				
				//Sets tile color and assigns value to the grid
				t.setTileColor(tileColor);
				Main.grid[x][y] = t;
			}
		}
	}	
	
	//Sets and smoothes out brightness
	private static Color GetColor(Color c, float br) 
	{	
		int r = (int)(c.getRed() * br);
		int g = (int)(c.getGreen() * br);
		int b = (int)(c.getBlue() * br);
		
		c = new Color(r, g, b);
		
		return c;
	}
	
		//---- UTILITY ----
		//Randomizes colors for each tile
		public static Color RandomizedColor() 
		{
			return new Color(rn.nextInt(255), rn.nextInt(255), rn.nextInt(255));
		}		
		//Clamps the values
		public static double Clamp(double a, double min, double max) 
		{
			if(a < min) a = min;
			if(a > max) a = max;
				
			return a;
		}	
		public static float WaterClamp(float val, float min, float max) {
		    return Math.max(min, Math.min(max, val));
		}	
		//---- LERP ----
		public static float Lerp(float a, float b, float f)
		{
		    return a + f * (b - a);
		}
		public static double Lerp(double a, double b, double f)
		{
		    return a + f * (b - a);
		}	
}
