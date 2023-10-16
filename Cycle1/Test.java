package Cycle1;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener; 
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;


public class Test extends JFrame{
	
	private JLabel bladeThicknessLabel;
	
	static JFrame frame = new JFrame("CutList Optimizer"); 
	JPanel innerPanel, innerSheetsPanel, panelNorth, panelEast, panelWest, panelMiddle;
	
	ArrayList<Wood> Boards  = new ArrayList<Wood>();
	ArrayList<Wood> Pieces  = new ArrayList<Wood>();
	double bladeThickness;
	

    public Test() {
    	 

//full screen window
    	CreateWindow();

        Container mainContainer = this.getContentPane();
        mainContainer.setLayout(new BorderLayout(1,2));
        mainContainer.setBackground(Color.WHITE);
        this.getRootPane().setBorder(BorderFactory.createMatteBorder(4,4,4,4, Color.WHITE));
        

//panels in container: panelNorth, panelEast, panelWest, panelMiddle;
        
//north panel         
        panelNorth = new JPanel();
        panelNorth.setBorder(new LineBorder(Color.BLACK, 2));
        panelNorth.setBackground(Color.WHITE);
        panelNorth.setLayout(new FlowLayout(5));
        mainContainer.add(panelNorth, BorderLayout.NORTH);
        
        JButton fileButton = new JButton("Open File");      
        JLabel fileNameLabel = new JLabel("Selected file: ");
        JTextArea fileContentTextArea = new JTextArea();
        
        
        panelNorth.add(fileButton);
        panelNorth.add(fileNameLabel);
        panelNorth.add(fileContentTextArea);
        

        fileButton.addActionListener(e -> {openFile(fileContentTextArea);});
        
        
        //west panel  
        panelWest = new JPanel();//new GridLayout(3,1,1,1));
        panelWest.setBorder(new LineBorder(Color.BLACK, 1));
        panelWest.setLayout(new BoxLayout(panelWest, BoxLayout.Y_AXIS));// FlowLayout(4,4,4));
        panelWest.setBackground(Color.WHITE);
        mainContainer.add(panelWest, BorderLayout.WEST);
        
    //panels
        JPanel gridPanel = new JPanel();
        gridPanel.setBorder(new LineBorder(Color.BLACK, 1));
        //gridPanel.setLayout(new FlowLayout(5));
        gridPanel.setBorder(new TitledBorder(null, "Wood Panels", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panelWest.add(gridPanel);
        
        innerPanel = new JPanel(new GridLayout(0,4,5,9));
        innerPanel.setBorder(new LineBorder(Color.BLACK, 1));
        innerPanel.setBackground(Color.LIGHT_GRAY);
        gridPanel.add(innerPanel);
        
        
    //stock sheets
        JPanel sheetsPanel = new JPanel();
        //sheetsPanel.setBorder(new LineBorder(Color.BLACK, 1));
        sheetsPanel.setBorder(new TitledBorder(null, "Stock Sheets", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panelWest.add(sheetsPanel); 
        
        innerSheetsPanel = new JPanel(new GridLayout(0,4,5,9));
        innerSheetsPanel.setBorder(new LineBorder(Color.BLACK, 1));
        innerSheetsPanel.setBackground(Color.LIGHT_GRAY);
        sheetsPanel.add(innerSheetsPanel);
        
   
        
    //blade width        
        JPanel bladePanel = new JPanel();
        //bladePanel.setBorder(new LineBorder(Color.BLACK, 1));
        bladePanel.setBorder(new TitledBorder(null, "Tools", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panelWest.add(bladePanel);

        JPanel innerBladePanel = new JPanel();
        innerBladePanel.setBorder(new LineBorder(Color.BLACK, 1));
        innerBladePanel.setBackground(Color.LIGHT_GRAY);
        bladePanel.add(innerBladePanel);

        JLabel bladeLabel = new JLabel("Blade Thickness"); 
        innerBladePanel.add(bladeLabel);
        JTextArea text = new JTextArea(1,10);
       text.setLineWrap(true);
        innerBladePanel.add(text);
        JButton b = new JButton("submit");
        innerBladePanel.add(b);
        
        //This is the Action listener to get the user input for blade thickness
        //for now it will be displayed in north panel near where the file is selected
        //will change where it will display later
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //This retrieves the text from the JTextField
                String bladeThicknessText = text.getText();

                //Checks for empty input
                if (bladeThicknessText.isEmpty()) {
                    JOptionPane.showMessageDialog(Test.this, 
                    		"Please enter a value.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                //Validates if the input is a valid double or integer
                try {
                    bladeThickness = Double.parseDouble(bladeThicknessText);
                    //Displays the blade thickness on the GUI
                    bladeThicknessLabel.setText("Blade Thickness: " + bladeThickness);
                    DrawAlg();
                    
                } catch (NumberFormatException ex) {
                    //Handle invalid input (not a double or integer)
                    JOptionPane.showMessageDialog(Test.this, 
                    		"Invalid input. Please enter a valid number.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
        
        
        
        
        
        //These lines depend on whether we are showing data in a 
        //textArea or as a label...this could add extra rows of textAreas
        //if needed if user can input info themselves w/o excel file
        //JButton addText = new JButton("Add");
        //addText.setLayout(new FlowLayout());
        
        //gridPanel.add(addText, BorderLayout.SOUTH);
        
//east panel        
        panelEast = new JPanel();
        panelEast.setBorder(new LineBorder(Color.BLACK, 1));
        panelEast.setLayout(new BoxLayout(panelEast, BoxLayout.Y_AXIS));
        //panelEast.setBackground(Color.WHITE);
        mainContainer.add(panelEast, BorderLayout.NORTH);

        
        //Global stats
        JPanel globalStats = new JPanel();
        globalStats.setBorder(new TitledBorder(null, "Global Statistics", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        globalStats.setPreferredSize(new Dimension(250, 75));
        panelEast.add(globalStats);
        
        JPanel innerGlobalStats = new JPanel();
        innerGlobalStats.setBorder(new LineBorder(Color.BLACK, 1));
        innerGlobalStats.setBackground(Color.WHITE);
      //Setting the layout so labels look good
        innerGlobalStats.setLayout(new BoxLayout(innerGlobalStats, BoxLayout.Y_AXIS));
        
        //Creating the labels
        addLabelWithSpace(innerGlobalStats, "Used stock sheets: ");
        addLabelWithSpace(innerGlobalStats, "Total used area: ");
        addLabelWithSpace(innerGlobalStats, "Total wasted area: ");
        addLabelWithSpace(innerGlobalStats, "Total cuts: ");
        addLabelWithSpace(innerGlobalStats, "Total cut length: ");
        bladeThicknessLabel = new JLabel("Blade Thickness: ");
        addLabelWithSpace(innerGlobalStats, "Optimization priority: ");
        
        bladeThicknessLabel.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));
        
        //adding certain labels to GUI and sets a preferred size for panel
        innerGlobalStats.add(bladeThicknessLabel);
        innerGlobalStats.setPreferredSize(new Dimension(225, 200));
        globalStats.add(innerGlobalStats);

//Sheet stats
        JPanel sheetStats = new JPanel();
        sheetStats.setBorder(new TitledBorder(null, "Sheet Statistics", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        sheetStats.setPreferredSize(new Dimension(250, 75));
        panelEast.add(sheetStats);
        
        JPanel innerSheetStats = new JPanel();
        innerSheetStats.setBorder(new LineBorder(Color.BLACK, 1));
        innerSheetStats.setBackground(Color.WHITE);
      //Setting the layout so labels look good
        innerSheetStats.setLayout(new BoxLayout(innerSheetStats, BoxLayout.Y_AXIS));
        
        addLabelWithSpace(innerSheetStats, "Stock sheet: ");
        addLabelWithSpace(innerSheetStats, "Used area: ");
        addLabelWithSpace(innerSheetStats, "Wasted area: ");
        addLabelWithSpace(innerSheetStats, "Cuts: ");
        addLabelWithSpace(innerSheetStats, "Cut length: ");
        addLabelWithSpace(innerSheetStats, "Panels: ");
        addLabelWithSpace(innerSheetStats, "Wasted panels: ");
        innerSheetStats.setPreferredSize(new Dimension(225, 200));
        sheetStats.add(innerSheetStats);
        
        //Cuts
        JPanel cutStats = new JPanel();
        cutStats.setBorder(new TitledBorder(null, "Cuts", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panelEast.add(cutStats);
        
        JPanel innerCutStats = new JPanel();
        innerCutStats.setBorder(new LineBorder(Color.BLACK, 1));
        innerCutStats.setBackground(Color.WHITE);
        
        JLabel numberLabel = new JLabel("#      ");
        JLabel panelLabel = new JLabel("Panel         ");
        JLabel cutLabel = new JLabel("Cut        ");
        JLabel resultLabel = new JLabel("Result");
        
        
        innerCutStats.add(numberLabel);
        innerCutStats.add(panelLabel);
        innerCutStats.add(cutLabel);
        innerCutStats.add(resultLabel);
        innerCutStats.setPreferredSize(new Dimension(225, 25));
        cutStats.add(innerCutStats);
       
//middle section        
        panelMiddle = new JPanel(new BorderLayout(2,2));
        panelMiddle.setPreferredSize(new Dimension(1500 , 1500));
        panelMiddle.setBorder(new LineBorder(Color.BLACK, 1));
       
//add to main frame  
        
        
        JScrollPane JSPW=new JScrollPane(panelWest, 
        		   ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,  
        		   ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        
        JScrollPane JSPC=new JScrollPane(panelMiddle, 
     		   ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,  
     		   ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        
        frame.add(JSPW,BorderLayout.WEST);
        frame.add(panelEast, BorderLayout.EAST);
        frame.add(panelNorth, BorderLayout.NORTH);
        frame.add(JSPC, BorderLayout.CENTER);
        
        frame.setVisible(true); 
        
    }
    
     public static void CreateWindow()
	{
    	 Dimension DimMax = Toolkit.getDefaultToolkit().getScreenSize();
 		frame.setSize(DimMax);
 		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
 		frame.setLocation(100,100);
 		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   
         ImageIcon icon = new ImageIcon("cutlist_icon.png");
         frame.setIconImage(icon.getImage());
	}
     
    private void openFile(JTextArea fileContentTextArea) {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            fileContentTextArea.setText(selectedFile.getName());

            try {
            	CutListAlgorithm(selectedFile.getAbsolutePath());

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
    }
    
  
	public void CutListAlgorithm(String name) throws IOException
	{
		CutList.main(name);
		ArrayList<Wood> List = CutList.GetWood();			
		CreateTable(List);
		panelMiddle.removeAll();
		
	}
	
	public void DrawAlg()
	{
		ArrayList<Wood> hold = new ArrayList<Wood>();;
		//hold = Javabin.alg(List);
		//panelMiddle.removeAll();
		JPanel ty = new JPanel() {
 		   @Override
 		   public void paint (Graphics g) {
 			  g.setColor(Color.LIGHT_GRAY);
 			  g.fillRect(0,0,panelMiddle.getWidth(),panelMiddle.getHeight());
 			  Canvas(g);
 		   }
 		   
 	   };

 	  panelMiddle.add(ty);
	}
	
	public void Canvas(Graphics g)
	{
		 Graphics2D g2d = (Graphics2D) g;
		 
		 int oldY = 0;
		 ArrayList<Rectangle> DrawRect = new ArrayList<Rectangle>();
		 for(Wood W : Boards)
		 {
			 g.setColor(Color.black);
			 g.fillRect(0-(int)bladeThickness, 0-(int)bladeThickness+oldY,(int)W.GetWidth() + ( 2*(int)bladeThickness),(int)W.GetHeight()+(2*(int)bladeThickness)); 
			 
			  g.setColor(Color.magenta);
			  DrawRect.add(new Rectangle(0, 0 +oldY,(int)W.GetWidth(),(int)W.GetHeight()));
			   g.fillRect(0, 0 +oldY,(int)W.GetWidth(),(int)W.GetHeight());
			   
	
			   
			   
			  // g.setColor(Color.blue);
			 //  g2d.setStroke(new BasicStroke((int)bladeThickness));
			//	g.drawRect(0-((int)bladeThickness/2), 0 +oldY - ((int)bladeThickness/2),(int)W.GetLength() + ( (int)bladeThickness),(int)W.GetWidth()+((int)bladeThickness));
			//	g.fillRect(0-(int)bladeThickness, 0-(int)bladeThickness+oldY,(int)W.GetLength() + ( (int)bladeThickness),(int)W.GetWidth()+((int)bladeThickness));   
		        
			   oldY+=(int)W.GetHeight()+ bladeThickness;
			   
			 
		 }
		 
		 for(Rectangle rectangle: DrawRect )
		 {
			// Calculate the coordinates of all four corners
		        int x1 = (int) (rectangle.getX());
		        int y1 = (int) (rectangle.getY());
		        int x2 = (int) (rectangle.getX() + rectangle.getWidth());
		        int y2 = (int) (rectangle.getY());
		        int x3 = (int) (rectangle.getX());
		        int y3 = (int) (rectangle.getY() + rectangle.getHeight());
		        int x4 = (int) (rectangle.getX() + rectangle.getWidth());
		        int y4 = (int) (rectangle.getY() + rectangle.getHeight());

		        // Print the coordinates of all four corners
		            System.out.println("Top-left corner: (" + x1 + ", " + y1 + ")");
		           System.out.println("Top-right corner: (" + x2 + ", " + y2 + ")");
		         System.out.println("Bottom-left corner: (" + x3 + ", " + y3 + ")");
		        System.out.println("Bottom-right corner: (" + x4 + ", " + y4 + ")");
				System.out.println();
		 } 
		 
		 boolean found = false;
		 int block;
		 for(Wood W : Pieces)
		 {
			 block = -1;
			 found = false;
			 for (Rectangle R : DrawRect)
			 {
				 oldY = 0;
				 if(R.getWidth() > W.GetWidth() && R.getHeight()>W.GetHeight() && !found)
				 {
					 found = true;
					 g.setColor(Color.blue);
					 g.fillRect((int)R.getX()-(int)bladeThickness, (int)R.getY()-(int)bladeThickness+oldY,(int)W.GetWidth() + ( 2*(int)bladeThickness),(int)W.GetHeight()+(2*(int)bladeThickness));
					 g.setColor(Color.green);
					  //DrawRect.add(new Rectangle((int)R.getX(), (int)R.getY() +oldY,(int)W.GetWidth(),(int)W.GetHeight()));
					   g.fillRect((int)R.getX(), (int)R.getY() +oldY,(int)W.GetWidth(),(int)W.GetHeight());
					   oldY+=(int)W.GetHeight()+ bladeThickness;
					   block = DrawRect.indexOf(R);
					  
				 }
			 }
			 
			if( found)
				 DrawRect.remove(block);
		 }
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		  g2d.setStroke(new BasicStroke(1)); 
		  g.setColor(Color.BLACK);
		  for(int x = 1; x<151 ; x++) {
		  g.drawLine(0,(int)bladeThickness*x,1500,(int)bladeThickness*x);
		  g.drawLine((int)bladeThickness*x,0,(int)bladeThickness*x,1500);
		  }
		 
		 
		
		 
	}
	
	private void CreateTable(ArrayList<Wood> List)
	{
		innerPanel.removeAll();
		if (!Boards.isEmpty())
		{
			Boards.clear();
		}
		if (!Pieces.isEmpty())
		{
			Pieces.clear();
		}
		
		JLabel length = new JLabel("Height");
        JLabel width = new JLabel("Width");
        JLabel quantity = new JLabel("Quantity     ");
        JLabel label0 = new JLabel("Label");
        
        innerPanel.add(length);
        innerPanel.add(width);
        innerPanel.add(quantity);
        innerPanel.add(label0);
        
        innerSheetsPanel.removeAll();
        
        JLabel length1 = new JLabel("Height");
        JLabel width1 = new JLabel("Width");
        JLabel quantity1 = new JLabel("Quantity     ");
        JLabel label1 = new JLabel("Label");
        
        innerSheetsPanel.add(length1);
        innerSheetsPanel.add(width1);
        innerSheetsPanel.add(quantity1);
        innerSheetsPanel.add(label1);
        
        
	
		for(Wood W : List){
			if(W.GetWoodtype().equals("Piece")) 
			{
				//System.out.println("Piece found"); 
		        	JTextArea label= new JTextArea(W.GetHeight()+"");
		        	label.setEditable(false);
		        	innerPanel.add(label);
		        	 label= new JTextArea(W.GetWidth()+"");
		        	innerPanel.add(label);
		        	 label= new JTextArea(W.GetAmount()+"");
		        	innerPanel.add(label);
		        	 label= new JTextArea(W.GetName()+"");
		        	innerPanel.add(label);
		        	for(int x = 1; x<= W.GetAmount(); x++)
		        	{
		        	Pieces.add(W);
		        	}
		        
				
			}
			else
			{
				//System.out.println("Wood found"); 
			 
	        	JTextArea label= new JTextArea(W.GetHeight()+"");
	        	//label.setEditable(true);
	        	innerSheetsPanel.add(label);
	        	 label= new JTextArea(W.GetWidth()+"");
	        	innerSheetsPanel.add(label);
	        	 label= new JTextArea(W.GetAmount()+"");
	        	innerSheetsPanel.add(label);
	        	 label= new JTextArea(W.GetName()+"");
	        	innerSheetsPanel.add(label);
	        	for(int x = 1; x<= W.GetAmount(); x++)
	        	{
	        	Boards.add(W);
	        	}
			}
		
			}  
		}
	
	
	private void addLabelWithSpace(JPanel panel, String labelText) {
        JLabel label = new JLabel(labelText);
        label.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
        panel.add(label);
    }
	
	public static void main(String[] args) {
        new Test();
    }

    
}