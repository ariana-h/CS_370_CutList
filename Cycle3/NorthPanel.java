import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

public class NorthPanel {
	static JPanel panelNorth;

	public static JPanel Create() {
		panelNorth = new JPanel(new GridLayout(1,2));
	    panelNorth.setBorder(new LineBorder(Color.BLACK, 2));
	    panelNorth.setBackground(Color.WHITE);
	    panelNorth.setLayout(new BoxLayout(panelNorth, BoxLayout.LINE_AXIS));
    
        panelNorth.add(InnerPanel.Files());
        panelNorth.add(Box.createHorizontalGlue());
        panelNorth.add(InnerPanel.Calc());
                  
		return panelNorth;
	}
}