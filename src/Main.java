import java.util.Random;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class Main {

	//---- OPTIONS ----
	public static int simulationSize = 250;
	public static int windowSize = 1000;
	public static boolean drawGrid = false;
	
	public static double noiseMax = 1;
	public static double noiseMin = 5;
	
	public static Color grassColor = new Color(49, 212, 0);
	public static Color sandColor = new Color(255, 225, 0);
	public static Color snowColor = new Color(255, 255, 255);
	public static Color stoneColor = new Color(0, 0, 0, 0);
	public static Color seaColor = new Color(3, 231, 252);
	
	public static float seaLevel = .2f;
	public static float sandReach = .1f;
	public static float snowLevel = .68f;
	public static float stoneLevel = .55f;
	
	//---- Coloring ----
	public static float landFadeoutMin = .6f;
	public static float waterFadeoutMin = .8f;
	
	public static Tile[][] grid;	
	public static Random rn = new Random();
	public static JFrame worldFrame = new JFrame();
	
	public static void main(String[] args) 
	{
		Initialize();
		InitializeWindow();
	}
	
	//---- INITIALIZATION ----
	//Initializes the grid
	public static void Initialize() 
	{
		grid = new Tile[simulationSize][simulationSize];
		
		double noiseWidth = 10;
		double noiseHeight = 10;
		
		double xRandom = rn.nextDouble() * noiseWidth;
		double yRandom = rn.nextDouble() * noiseWidth;
		
		noiseWidth += xRandom;
		noiseHeight += xRandom;
		
		for(int x = 0; x < Main.simulationSize; x++) 
		{
			for(int y = 0; y < Main.simulationSize; y++) 
			{
				Tile t = new Tile();
				
				t.setHeight(Math.abs((float)PerlinNoise.noise((double)x / noiseWidth + xRandom ,(double)y / noiseHeight + yRandom)));
				
				float brightness;			
				if(t.getHeight() < seaLevel) 
				{
					t.setTileColor(seaColor);
					brightness = clamp((float)(t.getHeight() / .15f), waterFadeoutMin, 1f);
					t.setType(TileType.Water);					
				}
				else 
				{
					brightness = clamp((float)(.35f / t.getHeight()), landFadeoutMin, 1f);
					t.setType(TileType.Grass);
					t.setTileColor(grassColor);
					
					//beaches generation
					if( t.getHeight() - seaLevel < sandReach ) 
					{
						brightness = clamp((float)(.25f / t.getHeight()), landFadeoutMin, 1f);
						t.setType(TileType.Sand);
						t.setTileColor(sandColor);
					}
					
					if(t.getHeight() >= stoneLevel) 
					{		
						brightness = clamp((float)(.5f / t.getHeight()), landFadeoutMin, 1f);
						t.setType(TileType.Stone);
						t.setTileColor(stoneColor);
					}
					
					if(t.getHeight() >= snowLevel) 
					{				
						brightness = clamp((float)(.7f / t.getHeight()), landFadeoutMin, 1f);
						t.setType(TileType.Snow);
						t.setTileColor(snowColor);
					}					
				}
				
				t.setBrightness(brightness);
				grid[x][y] = t;
			}
		}
	}
	
	//Creates simulation window
	public static void InitializeWindow() 
	{
		worldFrame.setSize(windowSize, windowSize);
		worldFrame.setResizable(false);
		worldFrame.add(new WorldFrame());
		worldFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		worldFrame.setVisible(true);
	}

	//---- CUSTOM ----
	//Randomizes colors for each tile
	public static void RandomizeColors() 
	{
		for(int x = 0; x < Main.simulationSize; x++) 
		{
			for(int y = 0; y < Main.simulationSize; y++) 
			{				
				Color c = new Color(rn.nextInt(255), rn.nextInt(255), rn.nextInt(255));
				grid[x][y].setTileColor(c);
			}
		}
	}
	
	public static float clamp(float val, float min, float max) {
	    return Math.max(min, Math.min(max, val));
	}
	public static double clamp(double val, double min, double max) {
	    return Math.max(min, Math.min(max, val));
	}
	
}
