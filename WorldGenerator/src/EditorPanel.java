import javax.swing.event.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class EditorPanel extends JPanel {
	
	private static final long serialVersionUID = 2L;
	
	private static JButton worldGegenerateButton = new JButton();
	
	//---- SEED ----
	private JTextField seedField = new JTextField();
	
	//---- SEA LEVEL ----
	private JLabel waterLevelLabel = new JLabel();
	private JSlider waterLevelSlider = new JSlider();
	
	private JLabel sandReachLabel = new JLabel();
	private JSlider sandReachSlider = new JSlider();
	
	private JLabel stoneLevelLabel = new JLabel();
	private JSlider stoneLevelSlider = new JSlider();
	
	private JLabel snowLevelLabel = new JLabel();
	private JSlider snowLevelSlider = new JSlider();
	
	private boolean initialize = false;
	
    @Override
    public void paint(Graphics g)    
	{ 	    	
    	super.paint(g);
    	super.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    	 
    	AssignListeners();
    	if(!initialize) Initialize();
    	
    	//---- GENERATION-BUTTON ----    	
    	worldGegenerateButton.setText("Generate");
    	super.add(worldGegenerateButton);
    	
    	//---- SEED ----
    	seedField.addActionListener(actionListener);
    	seedField.setActionCommand("seedChange");
    	seedField.setMaximumSize(new Dimension(getWidth(), 20));
    	
    	super.add(seedField);
    	
    	
    	//---- WATER LEVEL ----
    	waterLevelLabel.setText("Water level: " + waterLevelSlider.getValue());    	
    	super.add(waterLevelLabel);
    	
    	waterLevelSlider.setMinimum(-WorldGeneration.maxWorldHeight);
    	waterLevelSlider.setMaximum(WorldGeneration.maxWorldHeight);    
    	super.add(waterLevelSlider);    
    	
    	
    	//---- SAND REACH ----
    	sandReachLabel.setText("Sand reach: " + sandReachSlider.getValue());    	
    	super.add(sandReachLabel);
    	
    	sandReachSlider.setMinimum(0);
    	sandReachSlider.setMaximum(WorldGeneration.maxWorldHeight);    
    	super.add(sandReachSlider);       	
    	
    	
    	//---- STONE LEVEL ----
    	stoneLevelLabel.setText("Stone level: " + stoneLevelSlider.getValue());    	
    	super.add(stoneLevelLabel);
    	
    	stoneLevelSlider.setMinimum(-WorldGeneration.maxWorldHeight);
    	stoneLevelSlider.setMaximum(WorldGeneration.maxWorldHeight);    
    	super.add(stoneLevelSlider);  
    		
    	//---- SNOW LEVEL ----
    	snowLevelLabel.setText("Snow level: " + snowLevelSlider.getValue());    	
    	super.add(snowLevelLabel);
    	
    	snowLevelSlider.setMinimum(-WorldGeneration.maxWorldHeight);
    	snowLevelSlider.setMaximum(WorldGeneration.maxWorldHeight);    
    	super.add(snowLevelSlider);       	
	}
    
    private void Initialize() 
    {
    	seedField.setText("Enter world seed");
    	waterLevelSlider.setValue((int)(WorldGeneration.waterLevel * WorldGeneration.maxWorldHeight));
    	sandReachSlider.setValue((int)(WorldGeneration.sandReach * WorldGeneration.maxWorldHeight));
    	stoneLevelSlider.setValue((int)(WorldGeneration.stoneLevel * WorldGeneration.maxWorldHeight));
    	snowLevelSlider.setValue((int)(WorldGeneration.snowLevel * WorldGeneration.maxWorldHeight));
    	
    	initialize = true;
    }
    
    //Assigns listeners to JComponents
    private void AssignListeners() 
    {
    	worldGegenerateButton.removeActionListener(actionListener);
    	worldGegenerateButton.addActionListener(actionListener);  
    	worldGegenerateButton.setActionCommand("generate");
    	
    	waterLevelSlider.removeChangeListener(changeListener);
    	waterLevelSlider.addChangeListener(changeListener);
    	
    	sandReachSlider.removeChangeListener(changeListener);
    	sandReachSlider.addChangeListener(changeListener);
    	
    	stoneLevelSlider.removeChangeListener(changeListener);
    	stoneLevelSlider.addChangeListener(changeListener);
    	
    	snowLevelSlider.removeChangeListener(changeListener);
    	snowLevelSlider.addChangeListener(changeListener);
    }
    
    //Listens to button callback
	ActionListener actionListener = new ActionListener() 
	{	
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			if("generate".equals(e.getActionCommand())) 
			{
				try
				{
					WorldGeneration.worldSeed = Long.parseLong(seedField.getText());
				}
				catch(Exception exc)
				{
					WorldGeneration.worldSeed = 0;
				}
				
				WorldGeneration.GenerateWorld(false);
				Main.Repaint();
			}
    	}
	};
	
	//Listens to slider events
	ChangeListener changeListener = new ChangeListener() 
	{
		public void stateChanged(ChangeEvent e) 
        {       
        	if(e.getSource().equals(waterLevelSlider)) 
            {
        		//---- SEA LEVEL ---
        		WorldGeneration.waterLevel = ((float)waterLevelSlider.getValue() / (float)WorldGeneration.maxWorldHeight);
        		waterLevelLabel.setText("Water level: " + waterLevelSlider.getValue());     		
            }       
        	
        	if(e.getSource().equals(sandReachSlider)) 
        	{
        		WorldGeneration.sandReach = ((float)sandReachSlider.getValue() / (float)WorldGeneration.maxWorldHeight);
        		sandReachLabel.setText("Sand reach: " + sandReachSlider.getValue());
        	}
        	
        	if(e.getSource().equals(stoneLevelSlider)) 
        	{
        		WorldGeneration.stoneLevel = ((float)stoneLevelSlider.getValue() / (float)WorldGeneration.maxWorldHeight);
        		stoneLevelLabel.setText("Sand reach: " + stoneLevelSlider.getValue());
        	}
        	
        	if(e.getSource().equals(snowLevelSlider)) 
        	{
        		WorldGeneration.snowLevel = ((float)snowLevelSlider.getValue() / (float)WorldGeneration.maxWorldHeight);
        		snowLevelLabel.setText("Sand reach: " + snowLevelSlider.getValue());
        	}
        } 
		
	};       
}
