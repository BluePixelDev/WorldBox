import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class WorldFrame extends JPanel
{
	private static final long serialVersionUID = 1L;
	
	@Override
	public void paint(Graphics g) 
	{
		super.paint(g);
		
		int simulationScale = Main.windowSize / Main.simulationSize;
		
		int diffX = Main.windowSize - getWidth();
		int diffY = Main.windowSize - getHeight();
		
		for(int x = 0; x < Main.simulationSize; x++) 
		{
			for(int y = 0; y < Main.simulationSize; y++) 
			{	
				Tile t = Main.grid[x][y];
				g.setColor(t.getTileColor());
				g.fillRect(x * simulationScale - diffX / 2, y * simulationScale - diffY / 2, simulationScale * 2, simulationScale * 2);
				
				//---- DRAWS GRID ----
				if(!Main.drawGrid) continue;
				
				g.setColor(Color.black);
				g.drawRect(x * simulationScale - diffX / 2, y * simulationScale - diffY / 2, simulationScale * 2, simulationScale * 2);			
			}
		}
	}

}
