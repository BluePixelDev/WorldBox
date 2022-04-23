import javax.swing.event.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class EditorPanel extends JPanel {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 2L;
	
	static JButton worldGegenerateButton = new JButton();
	
	// slider
	static JLabel seaLevelLabel = new JLabel();
    static JSlider seaLevelSlider = new JSlider();
    
    static JLabel sandReachLabel = new JLabel();
    static JSlider sandReachSlider = new JSlider();
    
    static JLabel snowLevelLabel = new JLabel();
    static JSlider snowLevelSlider = new JSlider();
    
    static JLabel stoneLevelLabel = new JLabel();
    static JSlider stoneLevelSlider = new JSlider();
 
    @Override
    public void paint(Graphics g)    
	{ 	
    	super.paint(g);
    	super.setLayout(null);
    	
    	//---- GENERATION-BUTTON ----
    	super.add(worldGegenerateButton);
    	worldGegenerateButton.setText("Generate world");
    	worldGegenerateButton.setBounds(0, 0, 500, 20);
    	worldGegenerateButton.addActionListener(actionListener);   	
    	
    	//---- SEA LEVEL ---
    	seaLevelSlider.setValue((int)(WorldGeneration.seaLevel * 100));
    	seaLevelSlider.setBounds(0, 30, 250, 20);
    	seaLevelSlider.setMinimum(-100);
    	seaLevelSlider.setMaximum(100);    
	    seaLevelSlider.addChangeListener(new SliderListener());
    	super.add(seaLevelSlider);   
    	
    	seaLevelLabel.setText("Sea level: " + (float)seaLevelSlider.getValue()  / 100f);
    	seaLevelLabel.setBounds(280, 30, 250, 20);     	
    	super.add(seaLevelLabel);
    	
    	//---- SAND REACH ----
    	sandReachSlider.setValue((int)(WorldGeneration.sandReach * 100));
    	sandReachSlider.setBounds(0, 50, 250, 20);
    	sandReachSlider.setMinimum(-100);
    	sandReachSlider.setMaximum(100);    
    	sandReachSlider.addChangeListener(new SliderListener());
    	super.add(sandReachSlider);   
    	
    	sandReachLabel.setText("Sand reach: " + (float)sandReachSlider.getValue()  / 100f);
    	sandReachLabel.setBounds(280, 50, 250, 20);     	
    	super.add(sandReachLabel);
    	
    	//---- SNOW LEVEL ----
    	snowLevelSlider.setValue((int)(WorldGeneration.snowLevel * 100));
    	snowLevelSlider.setBounds(0, 80, 250, 20);
    	snowLevelSlider.setMinimum(-100);
    	snowLevelSlider.setMaximum(100);    
    	snowLevelSlider.addChangeListener(new SliderListener());
    	super.add(snowLevelSlider);   
    	
    	snowLevelLabel.setText("Snow level: " + (float)snowLevelSlider.getValue()  / 100f);
    	snowLevelLabel.setBounds(280, 80, 250, 20);     	
    	super.add(snowLevelLabel);
    	
    	//---- STONE LEVEL ----    	    	
    	stoneLevelSlider.setValue((int)(WorldGeneration.stoneLevel * 100));
    	stoneLevelSlider.setBounds(0, 110, 250, 20);
    	stoneLevelSlider.setMinimum(-100);
    	stoneLevelSlider.setMaximum(100);    
    	stoneLevelSlider.addChangeListener(new SliderListener());
    	super.add(stoneLevelSlider);   
        
    	stoneLevelLabel.setText("Stone level: " + (float)stoneLevelSlider.getValue()  / 100f);
    	stoneLevelLabel.setBounds(280, 110, 250, 20);     	
    	super.add(stoneLevelLabel);
    	
	}
    
    //Actions
	ActionListener actionListener = new ActionListener() 
	{	
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			if(e.getSource() == worldGegenerateButton) 
			{
				if(!WorldGeneration.isGenerating) 
				{
					WorldGeneration.worldSeed = 0;
					WorldGeneration.GenerateWorld(false);
					Main.UpdateSimulationWindow();
				}			
			}
    	}
	};
	
	//Class for slider events
    class SliderListener implements ChangeListener {
    	
        public void stateChanged(ChangeEvent e) 
        {       
        	if(e.getSource().equals(seaLevelSlider)) 
            {
        		//---- SEA LEVEL ---
        		WorldGeneration.seaLevel = (float)seaLevelSlider.getValue() / 100f;
        		seaLevelLabel.setText("Sea level: " + (float)seaLevelSlider.getValue()  / 100f);
            }
        	
            if(e.getSource().equals(sandReachSlider)) 
            {
            	//---- SAND REACH ----
            	WorldGeneration.sandReach = (float)sandReachSlider.getValue() / 100f;
            	sandReachLabel.setText("Sand reach: " + (float)sandReachSlider.getValue()  / 1000f);
            }
            
            if(e.getSource().equals(snowLevelSlider)) 
            {
            	//---- SNOW LEVEL ----
            	WorldGeneration.snowLevel = (float)snowLevelSlider.getValue() / 100f;
            	snowLevelLabel.setText("Snow level: " + (float)snowLevelSlider.getValue()  / 100f);
            }
            
            if(e.getSource().equals(stoneLevelSlider)) 
            {
            	 //---- STONE LEVEL ----
                WorldGeneration.stoneLevel = (float)stoneLevelSlider.getValue() / 100f;
                stoneLevelLabel.setText("Stone level: " + (float)stoneLevelSlider.getValue()  / 100f);
            }
         
        }
    }
}
