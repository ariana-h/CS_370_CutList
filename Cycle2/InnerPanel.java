import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class InnerPanel {
	static JLabel kerfThicknessLabel;
	static double kerfThickness;
	static JPanel innerPanel;
	static JPanel innerSheetsPanel;
	static JTextField text;

    public static JPanel Files() {
    	JPanel filesPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    	filesPanel.setBackground(Color.WHITE);
        JTextArea fileContentTextArea = new JTextArea();
	    JButton fileButton = new JButton("Open File");
	    fileButton.addActionListener(e -> {Buttons.openFile(fileContentTextArea);});
        JLabel fileNameLabel = new JLabel("Selected file: ");
        
        filesPanel.add(fileButton);
        filesPanel.add(fileNameLabel);
        filesPanel.add(fileContentTextArea);
		return filesPanel;
    }
    
    public static JPanel Calc() {
    	JPanel calcPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    	calcPanel.setBackground(Color.WHITE);
    	
    	JButton calcButton = new JButton("Calculate");
    	calcButton.addActionListener(e -> {Buttons.Draw();});
    	JButton saveButton = new JButton("Save");
    	
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
        innerKerfPanel.setLayout(new GridLayout(0,3,5,0));
        innerKerfPanel.setBorder(new LineBorder(Color.BLACK, 1));
        innerKerfPanel.setBackground(Color.LIGHT_GRAY);
        //innerKerfPanel.setPreferredSize(new Dimension(350, 200));
        
        JLabel kerfLabel = new JLabel("   Kerf Thickness"); 
        innerKerfPanel.add(kerfLabel);
        
        text = new JTextField(10);
        innerKerfPanel.add(text);
        text.addActionListener(e -> Buttons.submitAction());
        
        JButton b = new JButton("submit");
        innerKerfPanel.add(b);
        b.addActionListener(e -> Buttons.submitAction());
        
        innerKerfPanel.add(Checkbox("Grid"));
        innerKerfPanel.add(new JLabel());		//dummy space
        innerKerfPanel.add(new JLabel());
        innerKerfPanel.add(Checkbox("Wood Grain"));
        innerKerfPanel.add(new JLabel());
        innerKerfPanel.add(new JLabel());
        innerKerfPanel.add(Checkbox("Labels"));
        innerKerfPanel.add(new JLabel());
        innerKerfPanel.add(new JLabel());
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
	               if(s.compareTo("Measurements")==0)
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
	    addLabelWithSpace(innerGlobalStats, "Total used area: ");
	    addLabelWithSpace(innerGlobalStats, "Total wasted area: ");
	    addLabelWithSpace(innerGlobalStats, "Total cuts: ");
	    addLabelWithSpace(innerGlobalStats, "Total cut length: ");
	    kerfThicknessLabel = new JLabel("Kerf Thickness: ");
	    addLabelWithSpace(innerGlobalStats, "Optimization priority: ");
        
	    kerfThicknessLabel.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));
        innerGlobalStats.add(kerfThicknessLabel);
        innerGlobalStats.setPreferredSize(new Dimension(225, 200));
    
        return innerGlobalStats;
	}
	
	public static JPanel SheetStats() {
        JPanel innerSheetStats = new JPanel();
        innerSheetStats.setBorder(new LineBorder(Color.BLACK, 1));
        innerSheetStats.setBackground(Color.WHITE);
        innerSheetStats.setLayout(new BoxLayout(innerSheetStats, BoxLayout.Y_AXIS));
        
        addLabelWithSpace(innerSheetStats, "Stock sheet: ");
        addLabelWithSpace(innerSheetStats, "Used area: ");
        addLabelWithSpace(innerSheetStats, "Wasted area: ");
        addLabelWithSpace(innerSheetStats, "Cuts: ");
        addLabelWithSpace(innerSheetStats, "Cut length: ");
        addLabelWithSpace(innerSheetStats, "Panels: ");
        addLabelWithSpace(innerSheetStats, "Wasted panels: ");
        innerSheetStats.setPreferredSize(new Dimension(225, 200));
        
		return innerSheetStats;
	}
	
	public static JPanel CutStats() {
		JPanel innerCutStats = new JPanel();
        innerCutStats.setBorder(new LineBorder(Color.BLACK, 1));
        innerCutStats.setBackground(Color.WHITE);
        
        JLabel numberLabel = new JLabel("#      ");
        JLabel panelLabel = new JLabel("Panel         ");
        JLabel cutLabel = new JLabel("Cut        ");
        JLabel resultLabel = new JLabel("Result");
        
        innerCutStats.add(numberLabel);
        innerCutStats.add(panelLabel);
        innerCutStats.add(cutLabel);
        innerCutStats.add(resultLabel);
        innerCutStats.setPreferredSize(new Dimension(225, 25));
		
        return innerCutStats;
	}
	
	public static void addLabelWithSpace(JPanel panel, String labelText){
        JLabel label = new JLabel(labelText);
        label.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
        panel.add(label);
    }
	
	
}