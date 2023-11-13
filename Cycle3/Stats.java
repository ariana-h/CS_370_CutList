import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

public class Stats {
	public static JPanel Global() {
	    JPanel globalStats = new JPanel();
	    globalStats.setBorder(new TitledBorder(null, "Global Statistics", TitledBorder.LEADING, TitledBorder.TOP, null, null));
	    globalStats.setPreferredSize(new Dimension(250, 75));
	        
	    globalStats.add(InnerPanel.GlobalStats());
	     
		return globalStats;
	}
	
	public static JPanel Sheet() {
        JPanel sheetStats = new JPanel();
        sheetStats.setBorder(new TitledBorder(null, "Sheet Statistics", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        sheetStats.setPreferredSize(new Dimension(250, 75));
        
        sheetStats.add(InnerPanel.SheetStats());
        
		return sheetStats;
	}
	
	public static JPanel Cuts() {
        JPanel cutStats = new JPanel();
        cutStats.setBorder(new TitledBorder(null, "Cuts", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
        cutStats.add(InnerPanel.CutStats());
        
        return cutStats;
	}

	
}