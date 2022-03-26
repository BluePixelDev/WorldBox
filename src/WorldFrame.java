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
				
				g.setColor(ProcessBrigtness(t.getTileColor(), t.getBrightness()));
				g.fillRect(x * simulationScale - diffX / 2, y * simulationScale - diffY / 2, simulationScale * 2, simulationScale * 2);
				
				//---- DRAWS GRID ----
				if(!Main.drawGrid) continue;
				
				g.setColor(Color.black);
				g.drawRect(x * simulationScale - diffX / 2, y * simulationScale - diffY / 2, simulationScale * 2, simulationScale * 2);			
			}
		}
		
	}
	
	public static Color ProcessBrigtness(Color color, float brigtness) 
	{
		 float[] hsbValues = Color.RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(), null);
		
		int rgb = Color.HSBtoRGB(hsbValues[0], hsbValues[1], brigtness);
	    int red = (rgb >> 16) & 0xFF;
	    int green = (rgb >> 8) & 0xFF;
	    int blue = rgb & 0xFF;
	    
		return new Color(red, green, blue);
	}

}
