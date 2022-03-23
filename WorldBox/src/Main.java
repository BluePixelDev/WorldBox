import java.util.Random;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class Main {

	//---- OPTIONS ----
	public static int simulationSize = 100;
	public static int windowSize = 1000;
	public static boolean drawGrid = false;
	
	public static double noiseWidth = 150;
	public static double noiseHeight = 150;

	
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
		
		for(int x = 0; x < Main.simulationSize; x++) 
		{
			for(int y = 0; y < Main.simulationSize; y++) 
			{
				Tile t = new Tile();							
				
				int rgb = Color.HSBtoRGB(0, 0, Math.abs((float)PerlinNoise.noise((double)x / noiseWidth ,(double)y / noiseHeight)));
			    int red = (rgb >> 16) & 0xFF;
			    int green = (rgb >> 8) & 0xFF;
			    int blue = rgb & 0xFF;
			    
				t.setTileColor(new Color(red, green, blue));
				
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
}
