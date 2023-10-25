import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

public class InnerPanel {
	static JLabel kerfThicknessLabel;
	static double kerfThickness;
	static JPanel innerPanel;
	static JPanel innerSheetsPanel;
    static JTextArea text;
	
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
        innerKerfPanel.setBorder(new LineBorder(Color.BLACK, 1));
        innerKerfPanel.setBackground(Color.LIGHT_GRAY);
        
        JLabel kerfLabel = new JLabel("Kerf Thickness"); 
        innerKerfPanel.add(kerfLabel);
        text = new JTextArea(1,10);
        text.setLineWrap(true);
        innerKerfPanel.add(text);
        JButton b = new JButton("submit");
        innerKerfPanel.add(b);

        b.addActionListener(e -> Buttons.submitAction());
        
		return innerKerfPanel;
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
