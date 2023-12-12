import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

public class PanelGrid {
	
	public static JPanel WoodPanels() {
		JPanel gridPanel = new JPanel();
        gridPanel.setBorder(new LineBorder(Color.BLACK, 1));
        gridPanel.setBorder(new TitledBorder(null, "Wood Panels", TitledBorder.LEADING, TitledBorder.TOP, null, null));

        gridPanel.add(InnerPanel.Panels());
    
        return gridPanel;
	}
	
	public static JPanel StockSheets() {
        JPanel sheetsPanel = new JPanel();
        sheetsPanel.setBorder(new TitledBorder(null, "Stock Sheets", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        
        sheetsPanel.add(InnerPanel.Sheets());
        
		return sheetsPanel;
	}
	
	public static JPanel KerfPanel() {
	    JPanel kerfPanel = new JPanel();
	    kerfPanel.setBorder(new TitledBorder(null, "Tools", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        kerfPanel.add(InnerPanel.Kerf());


	    
	    return kerfPanel;


	}
}
