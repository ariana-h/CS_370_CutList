import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

public class EastPanel {
	static JPanel panelEast;
	
	public static JPanel Create() {
		panelEast = new JPanel();
        panelEast.setBorder(new LineBorder(Color.BLACK, 1));
        panelEast.setLayout(new BoxLayout(panelEast, BoxLayout.Y_AXIS));
        
	    panelEast.add(Stats.Global());
        
		return panelEast;

	}
}