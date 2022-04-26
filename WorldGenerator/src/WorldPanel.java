import java.awt.*;    
import javax.swing.*;

public class WorldPanel extends JPanel
{
	private static final long serialVersionUID = 1L;

	@Override
	public void paint(Graphics g) 
	{	
		setLayout(new BorderLayout(1, 1));
		setPreferredSize(new Dimension(350, 190));
		    
		g.drawImage(WorldGeneration.worldImage, 0, 0,  Main.windowWidth,  Main.windowHeight,  this);
			
		g.setColor(Color.black);
		g.drawString("SEED: " + Long.toString(WorldGeneration.worldSeed), 0, 11);
		g.drawString("TILES: " + Main.simulationSize * Main.simulationSize, 0, 23);
		
		g.setColor(Color.white);
		g.drawString("SEED: " + Long.toString(WorldGeneration.worldSeed), 0, 10);
		g.drawString("TILES: " + Main.simulationSize * Main.simulationSize, 0, 22);
				
	}
}
