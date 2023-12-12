
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.DecimalFormat;
import javax.swing.*;
public class CreateGUI extends JFrame{
	DecimalFormat BLT = new DecimalFormat("0.000");
	static JFrame frame = new JFrame("CutList Optimizer");
    public CreateGUI(){
    	CreateWindow();  
    	
    	MainContainer.Create(this.getContentPane(), this.getRootPane());
    	
    	//north panel         
        MainContainer.mainContainer.add(NorthPanel.Create(), BorderLayout.NORTH);
        //west panel  
        MainContainer.mainContainer.add(WestPanel.Create(), BorderLayout.WEST);
        //east panel        
        MainContainer.mainContainer.add(EastPanel.Create(), BorderLayout.NORTH);
        //Middle panel
        MainContainer.mainContainer.add(MiddlePanel.Create());
        
        frame.add(ScrollPanes.JSPW(),BorderLayout.WEST);
        frame.add(EastPanel.panelEast, BorderLayout.EAST);
        frame.add(NorthPanel.panelNorth, BorderLayout.NORTH);
        frame.add(ScrollPanes.JSPC(), BorderLayout.CENTER);
        
        frame.setVisible(true); 
    }
    public static void CreateWindow(){
    	Dimension DimMax = Toolkit.getDefaultToolkit().getScreenSize();
   	 	frame.setSize(DimMax);
   	 	frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
   	 	frame.setLocation(100,100);
   	 	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  
        ImageIcon icon = new ImageIcon("cutlist_icon.png");
        frame.setIconImage(icon.getImage());
	}
    
    
	public static void main(String[] args){
		
       

        	new CreateGUI();
        	SwingUtilities.invokeLater(() -> {

                // Create an action to be performed when the key combination is pressed
                AbstractAction ZoomIn = new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // Trigger the drawing of a square in the panel
                    	if(Algorithm.ty != null)
                    	{
                    		if(Algorithm.scale<10 *InnerPanel.kerfThickness)
                    			Algorithm.scale=1;
                    		Algorithm.zoomIn();
                    	}
                    } };
                AbstractAction ZoomOut = new AbstractAction() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            // Trigger the drawing of a square in the panel
                        	if(Algorithm.ty != null)
                        	{
                        		if(Algorithm.scale >=1)
                        			Algorithm.scale=(1/1.02);
                            Algorithm.zoomOut();
                        	}
                        } };    

                
                KeyStroke keyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_EQUALS, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask());
                KeyStroke keyStroke1 = KeyStroke.getKeyStroke(KeyEvent.VK_MINUS, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask());
                MiddlePanel.panelMiddle.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(keyStroke, "ZoomIn");
                MiddlePanel.panelMiddle.getActionMap().put("ZoomIn", ZoomIn);
                MiddlePanel.panelMiddle.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(keyStroke1, "ZoomOut");
                MiddlePanel.panelMiddle.getActionMap().put("ZoomOut", ZoomOut);

            });
        
    }


   
}
