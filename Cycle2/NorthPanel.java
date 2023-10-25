import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

public class NorthPanel {
	static JPanel panelNorth;

	public static JPanel Create() {
		panelNorth = new JPanel();
	    panelNorth.setBorder(new LineBorder(Color.BLACK, 2));
	    panelNorth.setBackground(Color.WHITE);
	    panelNorth.setLayout(new FlowLayout(5));
        JTextArea fileContentTextArea = new JTextArea();	 
        
	    JButton fileButton = new JButton("Open File");
	    fileButton.addActionListener(e -> {Buttons.openFile(fileContentTextArea);});
	  
        JLabel fileNameLabel = new JLabel("Selected file: ");
	    
        panelNorth.add(fileButton);
        panelNorth.add(fileNameLabel);
        panelNorth.add(fileContentTextArea);
             
		return panelNorth;
	}
}
