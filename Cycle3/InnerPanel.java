import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class InnerPanel {
	static JLabel kerfThicknessLabel , UsedArea , WastedArea;
	static double kerfThickness;
	static JPanel innerPanel;
	static JPanel innerSheetsPanel;
	static JTextField text;

    public static JPanel Files() {
    	JPanel filesPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    	filesPanel.setBackground(Color.WHITE);
        JTextArea fileContentTextArea = new JTextArea();
	    JButton fileButton = new JButton("File");  
	    Buttons.addFileButton(fileButton, fileContentTextArea);

        fileButton.setBounds(10, 10, 100, 30);
        filesPanel.add(fileButton);
        filesPanel.setSize(200, 100);
        
        JLabel fileNameLabel = new JLabel("Selected file: ");

        filesPanel.add(fileNameLabel);
        filesPanel.add(fileContentTextArea);
		return filesPanel;
    }
    
    public static JPanel Calc() {
    	JPanel calcPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    	calcPanel.setBackground(Color.WHITE);
    	
    	JButton calcButton = new JButton("Calculate");
    	calcButton.addActionListener(e -> {Buttons.Draw();});
    	JTextArea fileContentTextArea = new JTextArea();
    	JButton saveButton = new JButton("Save");

   	saveButton.addActionListener(e -> {Buttons.captureScreen(CreateGUI.frame, fileContentTextArea);});
    	
    	calcPanel.add(fileContentTextArea);
    	
    	calcPanel.add(calcButton);
    	calcPanel.add(saveButton);
    	
		return calcPanel;    
    }

	public static JPanel Panels() {
		innerPanel = new JPanel();
        innerPanel = new JPanel(new GridLayout(0,4,5,9));
        innerPanel.setBorder(new LineBorder(Color.BLACK, 1));
        innerPanel.setBackground(Color.LIGHT_GRAY);
		return innerPanel;
	}
	
	public static JPanel Sheets() {
		innerSheetsPanel = new JPanel();
        innerSheetsPanel = new JPanel(new GridLayout(0,4,5,9));
        innerSheetsPanel.setBorder(new LineBorder(Color.BLACK, 1));
        innerSheetsPanel.setBackground(Color.LIGHT_GRAY);
		return innerSheetsPanel;
	}
	
	public static JPanel Kerf() {
	    JPanel innerKerfPanel = new JPanel();
	    innerKerfPanel.setLayout(new BoxLayout(innerKerfPanel, BoxLayout.Y_AXIS));
	    innerKerfPanel.setBackground(Color.LIGHT_GRAY); 
	    innerKerfPanel.setBorder(new LineBorder(Color.BLACK, 1));
	    //innerKerfPanel.setPreferredSize(new Dimension(300, 200));
	    
	    JPanel kerfRowPanel = new JPanel();
	    kerfRowPanel.setLayout(new BoxLayout(kerfRowPanel, BoxLayout.X_AXIS));
	    kerfRowPanel.setBackground(Color.LIGHT_GRAY);
        
	    JLabel kerfLabel = new JLabel("Kerf Thickness ");
	    kerfRowPanel.add(kerfLabel);;
        
        text = new JTextField(10);
        text.setMaximumSize(new Dimension(90, 20));
	    kerfRowPanel.add(text);
        text.addActionListener(e -> Buttons.submitAction());
        
        JButton b = new JButton("submit");
        kerfRowPanel.add(b);
        b.addActionListener(e -> Buttons.submitAction());
        
	    innerKerfPanel.add(kerfRowPanel);
	    innerKerfPanel.add(Checkbox("Grid"));
	    innerKerfPanel.add(Checkbox("Wood Grain"));
	    innerKerfPanel.add(Checkbox("Labels"));
	    innerKerfPanel.add(Checkbox("Dimensions"));
	    return innerKerfPanel;

	}
	
	public static JPanel Checkbox(String s)
	{
		//Font f = new Font("serrif",0,10);
		JPanel CB = new JPanel (new FlowLayout (FlowLayout.LEFT));
		CB.setBackground(Color.LIGHT_GRAY);
		 JCheckBox c1 = new JCheckBox(s);
		 	//c1.setFont(f);
	        c1.setBackground(Color.LIGHT_GRAY);
	        c1.setMnemonic(s.charAt(0));
	        
	        c1.addItemListener(new ItemListener() {
	            @Override
	            public void itemStateChanged(ItemEvent e)
	            {
	               if(s.compareTo("Grid")==0)
	            	   Buttons.Grid(e);
	               if(s.compareTo("Wood Grain")==0)
	               {
	            	   Buttons.WoodGrain(e);
	               }
	               if(s.compareTo("Labels")==0)
	               {
	            	   Buttons.Label(e);
	               }
	               if(s.compareTo("Dimensions")==0)
	               {
	            	   Buttons.Measure(e);
	               }
	            }});
	        
	        
	        CB.add(c1 , BorderLayout.WEST);
		
		
		return CB;
	}
	
	
	
	

	public static JPanel GlobalStats() {
		JPanel innerGlobalStats = new JPanel();
        innerGlobalStats.setBorder(new LineBorder(Color.BLACK, 1));
        innerGlobalStats.setBackground(Color.WHITE);
        innerGlobalStats.setLayout(new BoxLayout(innerGlobalStats, BoxLayout.Y_AXIS));
		
	    //Creating the labels
	    addLabelWithSpace(innerGlobalStats, "Used stock sheets: ");
	    UsedArea = new JLabel("Total Used Area: ");
	    //addLabelWithSpace(innerGlobalStats, "Total used area: ");
	    WastedArea = new JLabel("Total Wasted Area: ");
	    //addLabelWithSpace(innerGlobalStats, "Total wasted area: ");
	    addLabelWithSpace(innerGlobalStats, "Total cuts: ");
	    addLabelWithSpace(innerGlobalStats, "Total cut length: ");
	    kerfThicknessLabel = new JLabel("Kerf Thickness: 0 in.");
	    addLabelWithSpace(innerGlobalStats, "Optimization priority: Best Fit");
        
	    kerfThicknessLabel.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
	    UsedArea.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
	    WastedArea.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
        innerGlobalStats.add(kerfThicknessLabel);
        innerGlobalStats.add(UsedArea);
        innerGlobalStats.add(WastedArea);
        innerGlobalStats.setPreferredSize(new Dimension(225, 200));
    
        return innerGlobalStats;
	}
	
	public static void addLabelWithSpace(JPanel panel, String labelText){
        JLabel label = new JLabel(labelText);
        label.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
        panel.add(label);
    }
	
	
}