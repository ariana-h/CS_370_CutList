import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class MiddlePanel {
	static JPanel panelMiddle = new JPanel(new BorderLayout(2,2));;
	
	public static JPanel Create() {
        //panelMiddle = new JPanel(new BorderLayout(2,2));
        panelMiddle.setPreferredSize(new Dimension(12000 , 12000));
        panelMiddle.setBorder(new LineBorder(Color.BLACK, 1));

        return panelMiddle;
	}

}
