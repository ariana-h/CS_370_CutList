import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
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
