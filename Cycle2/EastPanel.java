import java.awt.Color;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class EastPanel {
	static JPanel panelEast;
	
	public static JPanel Create() {
		panelEast = new JPanel();
        panelEast.setBorder(new LineBorder(Color.BLACK, 1));
        panelEast.setLayout(new BoxLayout(panelEast, BoxLayout.Y_AXIS));
        
	    panelEast.add(Stats.Global());
        panelEast.add(Stats.Sheet());
        panelEast.add(Stats.Cuts());
        
		return panelEast;

	}
}
