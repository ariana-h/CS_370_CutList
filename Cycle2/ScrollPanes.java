import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class ScrollPanes {
	static JScrollPane JSPW;
	static JScrollPane JSPC;
	
	public static JScrollPane JSPW(){
        return JSPW=new JScrollPane(WestPanel.panelWest, 
     		   ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,  
     		   ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
	}
	
	public static JScrollPane JSPC() {
        return JSPC=new JScrollPane(MiddlePanel.panelMiddle, 
      		   ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,  
      		   ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
	}
	
}
