import java.awt.Color;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class WestPanel {
		static JPanel panelWest = new JPanel();
		
	public static JPanel Create() {
	        panelWest.setBorder(new LineBorder(Color.BLACK, 1));
	        panelWest.setLayout(new BoxLayout(panelWest, BoxLayout.Y_AXIS));
	        panelWest.setBackground(Color.WHITE);
	        
	        panelWest.add(PanelGrid.WoodPanels());
	        panelWest.add(PanelGrid.StockSheets()); 
		    panelWest.add(PanelGrid.KerfPanel());

			return panelWest;
	}
}
