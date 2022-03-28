import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class Main {

	//---- OPTIONS ----
	public static int simulationSize = 250;
	public static int windowSize = 1000;
	
	public static boolean drawGrid = false;
	
	public static Tile[][] grid;	
	public static JFrame worldFrame = new JFrame();
	
	public static void main(String[] args) 
	{
		WorldGeneration.GenerateWorld();
		InitializeWindow();
	}
	
	//---- INITIALIZATION ----
	//Initializes the grid
	
	//Creates simulation window
	public static void InitializeWindow() 
	{
		worldFrame.setSize(windowSize, windowSize);
		worldFrame.setResizable(false);
		worldFrame.add(new WorldFrame());
		worldFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		worldFrame.setVisible(true);
	}	
}
