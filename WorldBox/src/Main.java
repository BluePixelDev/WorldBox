import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class Main {

	//---- OPTIONS ----
	public static int simulationSize = 250;
	public static int windowSize = 1000;
	
	public static boolean drawGrid = false;
	
	public static Tile[][] grid;	
	public static JFrame worldFrame = new JFrame();
	public static JFrame editorFrame = new JFrame();
	
	public static void main(String[] args) 
	{
		WorldGeneration.GenerateWorld();
		InitializeWorldWindow();
		InitializeEditorWindow();
	}
	
	//---- INITIALIZATION ----
	//Initializes the grid
	
	//Repaints the window
	public static void UpdateSimulationWindow() 
	{
		worldFrame.repaint();
	}
	
	//Creates simulation window
	public static void InitializeWorldWindow() 
	{
		worldFrame.setSize(windowSize, windowSize);
		worldFrame.setResizable(false);
		worldFrame.add(new WorldFrame());
		worldFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		worldFrame.setVisible(true);
	}	
	
	public static void InitializeEditorWindow() 
	{
		editorFrame.setSize(500, 500);
		editorFrame.setResizable(false);
		editorFrame.add(new EditorFrame());
		editorFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		editorFrame.setVisible(true);
	}	
}
