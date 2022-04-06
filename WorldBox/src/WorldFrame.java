import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class WorldFrame extends JPanel
{
	private static final long serialVersionUID = 1L;
	
	@Override
	public void paint(Graphics g) 
	{	
		g.drawImage(WorldGeneration.worldImage, 0, 0,  getWidth(), getHeight(),  this);
			
		g.setColor(Color.black);
		g.drawString("SEED: " + Long.toString(WorldGeneration.worldSeed), 0, 10);
		g.drawString("TILES: " + Main.simulationSize * Main.simulationSize, 0, 22);
		
	}
}
