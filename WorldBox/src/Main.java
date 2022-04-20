import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class Main {

	//---- OPTIONS ----
	public static int simulationSize = 500;
	public static int windowWidth = 1000;
	public static int windowHeight = 1000;
	
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
		worldFrame.setSize(windowWidth, windowHeight);
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
