import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;

import javax.swing.BorderFactory;
import javax.swing.JRootPane;

public class MainContainer {
	static Container mainContainer;
	
	public static void Create(Container c, JRootPane r) {
		mainContainer = c;
        mainContainer.setLayout(new BorderLayout(1,2));
        mainContainer.setBackground(Color.WHITE);
        r.setBorder(BorderFactory.createMatteBorder(4,4,4,4, Color.WHITE));
	}
}
