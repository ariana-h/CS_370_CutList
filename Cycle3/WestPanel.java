import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

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
