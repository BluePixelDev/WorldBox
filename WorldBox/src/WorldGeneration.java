import java.awt.Color;
import java.util.Random;
import java.awt.Image;
import java.awt.image.BufferedImage;

public class WorldGeneration 
{
	public static long worldSeed = 0;	
	public static int maxWorldHeight = 20;
	
	//---- COLOR SETTINGS ----
	public static Color grassColor = new Color(92, 166, 31);	
	public static Color sandColor = new Color(218, 207, 143);
	
	public static Color stoneColor = new Color(120, 120, 120);
	public static Color snowColor = Color.white;
	
	public static Color waterColor = new Color(49, 109, 195);
	
	public static float seaLevel = .0f;
	public static float sandReach = .05f;
	public static float snowLevel = .6f;
	public static float stoneLevel = .4f;
	
	//---- Coloring ----	
	public static float waterFadeout = .2f;
	
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
		
		for(int x = 0; x < Main.simulationSize; x++) 
		{
			for(int y = 0; y < Main.simulationSize; y++) 
			{
				Tile t = new Tile();
				Tile tUp = null; //Tile above current tile
						
				if(y != 0) tUp = Main.grid[x][y-1];
				
				//---- NOISE GENERATION ----
				double noise1 = noise.eval((double)x / (double)24, (double)y / (double)24, 1);
				double noise2 = noise.eval((double)x / (double)8, (double)y / (double)8, .5f);	
				double combined1 = Lerp(noise1, noise2, .5d);
				
				double noise3 = noise.eval((double)x / (double)16, (double)y / (double)16, 1);	
				double combined2 = Lerp(combined1, noise3, .5d);
				
				double noise4 = noise.eval((double)x / (double)64, (double)y / (double)64, 0);
				double combined3 = Clamp(combined2 + noise4, -1, 1);	
				
				double noise5 = noise.eval((double)x / (double)128, (double)y / (double)24, 0);
				double combined4 = Lerp(noise5, combined3, .8d);
				
				float heightStep =  1 / (float)maxWorldHeight;
				float finalHeight = heightStep * (Math.round(combined4 / heightStep));
				
				t.setHeight(finalHeight);
				
				Color tileColor = waterColor;
				
				//---- WATER ----
				if(t.getHeight() < seaLevel) 
				{					
					
					tileColor =  t.getHeight() > seaLevel - waterFadeout ?  SetColorBrightness(waterColor, 1.2f) : waterColor; 						
					
					t.setType(TileType.Water);	
					t.setBiome(BiomeType.Sea);
					
				}
				else 
				{					
					tileColor = grassColor;
					t.setType(TileType.Grass);
					t.setBiome(BiomeType.Plains);
					
					//---- BEACHES ----
					if( t.getHeight() - seaLevel < sandReach ) 
					{									
						t.setType(TileType.Sand);		
						tileColor = sandColor;		
						t.setBiome(BiomeType.Beach);
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
					
					if(tUp != null) 
					{
						//---- HEIGHT COLORING ----
						if(tUp.getHeight() < t.getHeight()) 
						{
							tileColor = SetColorBrightness(tileColor, 1.1f);
						}
						
						if(tUp.getHeight() > t.getHeight()) 
						{
							tileColor = SetColorBrightness(tileColor, .8f);
						}
					}
					
							
				}				
				
				//Adds tile to buffered image
				wMap.setRGB(x, y, tileColor.getRGB());
				worldImage = wMap;
				
				//Sets tile color and assigns value to the grid
				t.setColor(tileColor);
				Main.grid[x][y] = t;
			}
		}
	}	
	
	private static Color SetColorBrightness(Color c, float br) 
	{	
		int r = Clamp((int)(c.getRed() * br), 0, 255);
		int g = Clamp((int)(c.getGreen() * br), 0, 255);
		int b = Clamp((int)(c.getBlue() * br), 0, 255);
		
		c = new Color(r, g, b);
		
		return c;
	}
	
	//Clamps value between minimal and maximal value
	public static double Clamp(double a, double min, double max) 
	{
		if(a < min) a = min;
		if(a > max) a = max;
			
		return a;
	}	
	public static int Clamp(int a, int min, int max) 
	{
		if(a < min) a = min;
		if(a > max) a = max;
			
		return a;
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
