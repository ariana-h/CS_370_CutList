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

}
