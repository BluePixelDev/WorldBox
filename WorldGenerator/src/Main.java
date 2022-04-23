import javax.swing.JFrame;
import javax.swing.WindowConstants;
import GenUtility.NoiseMode;
import GenUtility.NoiseNode;
import GenUtility.Tile;

public class Main {

	//---- OPTIONS ----
	public static int simulationSize = 250;
	public static int windowWidth = 1000;
	public static int windowHeight = 1000;
	
	public static Tile[][] grid;	
	public static JFrame worldFrame = new JFrame();
	public static JFrame editorFrame = new JFrame();
	
	public static void main(String[] args) 
	{
		NoiseNode n1 = new NoiseNode(800, 800, NoiseMode.Lerp);
		n1.setLerpAmount(.01);
		NoiseManager.noiseNodes.add(n1);
		
		NoiseNode n2 = new NoiseNode(100, 100, NoiseMode.Lerp);
		n2.setLerpAmount(.65);
		NoiseManager.noiseNodes.add(n2);
		
		NoiseNode n4 = new NoiseNode(10, 10, NoiseMode.Lerp);
		n4.setLerpAmount(.2);
		NoiseManager.noiseNodes.add(n4);
		
		NoiseNode n5 = new NoiseNode(40, 40, NoiseMode.Lerp);
		n5.setInvert(true);
		n5.setLerpAmount(.4);
		NoiseManager.noiseNodes.add(n5);
		
		WorldGeneration.GenerateWorld(true);
		InitializeWindows();
	}
	
	//Repaints the window
	public static void UpdateSimulationWindow() 
	{
		worldFrame.repaint();
	}	
	//Creates simulation window
	public static void InitializeWindows() 
	{
		WorldPanel w = new WorldPanel();	
	
		worldFrame.setSize(windowWidth, windowHeight);
		worldFrame.setResizable(false);
		worldFrame.add(w);
		worldFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		worldFrame.setVisible(true);
		
		
		EditorPanel f = new EditorPanel();
		editorFrame.setSize(500, 500);
		editorFrame.setResizable(false);
		editorFrame.add(f);
		editorFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		editorFrame.setVisible(true);
	}	
}