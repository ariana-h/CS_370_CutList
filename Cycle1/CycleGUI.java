package Cycle1;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener; 
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
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


public class CycleGUI extends JFrame{
	
	private JLabel bladeThicknessLabel;
	
	static JFrame frame = new JFrame("CutList Optimizer"); 
	JPanel innerPanel, innerSheetsPanel, panelNorth, panelEast, panelWest, panelMiddle;
	
	ArrayList<Wood> Boards  = new ArrayList<Wood>();
	ArrayList<Wood> Pieces  = new ArrayList<Wood>();
	double bladeThickness;
	
	DecimalFormat BLT = new DecimalFormat("0.000");
	

    public CycleGUI() {
    	 

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
                    JOptionPane.showMessageDialog(CycleGUI.this, 
                    		"Please enter a value.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                int numSlash = 0;
                for(int i = 0 ; i < bladeThicknessText.length() ; i++ )
                {
                	char c = bladeThicknessText.charAt(i);
                	
                	if(c==47)
                		numSlash++;
                }
                

                //Validates if the input is a valid double or integer
                if(numSlash == 0)
                try {
                    bladeThickness = Double.parseDouble(bladeThicknessText);
                    //Displays the blade thickness on the GUI
                    bladeThicknessLabel.setText("Blade Thickness: " + bladeThickness);
                    DrawAlg();
                    
                } catch (NumberFormatException ex) {
                    //Handle invalid input (not a double or integer)
                    JOptionPane.showMessageDialog(CycleGUI.this, 
                    		"Invalid input. Please enter a valid number.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                }
                
                if(numSlash > 1 || bladeThicknessText.charAt(0)==47 || bladeThicknessText.charAt(bladeThicknessText.length()-1)==47) {
                	JOptionPane.showMessageDialog(CycleGUI.this, 
                    		"Invalid input. Please enter a valid fraction.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                	
                }
                
                else if(numSlash == 1)
                {
                	String[] numbers = new String[2];
                	numbers = bladeThicknessText.split("/");

                		try {
                			double num1 =Double.parseDouble(numbers[0]);
                			double num2 =Double.parseDouble(numbers[1]);
                			
                			if(num2 == 0) 
                			{
                		JOptionPane.showMessageDialog(CycleGUI.this, 
                        		"Invalid input. Cannot divide by 0.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                			}
                			else {
                			bladeThickness = num1/num2;
                			bladeThickness = Double.parseDouble(BLT.format(bladeThickness));
                            bladeThicknessLabel.setText("Blade Thickness: " + bladeThicknessText);
                            DrawAlg();
                			}
                            
                        } catch (NumberFormatException ex) {
                            //Handle invalid input (not a double or integer)
                            JOptionPane.showMessageDialog(CycleGUI.this, 
                            		"Invalid input. Please enter a valid fraction with numbers.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                            
                        }
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
        panelMiddle.setPreferredSize(new Dimension(12000 , 12000));
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
		
	}
	
	public void DrawAlg()
	{
		ArrayList<Wood> hold = new ArrayList<Wood>();;
		//hold = Javabin.alg(List);
		panelMiddle.removeAll();
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
		 
		 g2d.setStroke(new BasicStroke(1)); 
		  g.setColor(Color.BLACK);
		  for(int x = 1; x<(int)(panelMiddle.getWidth()/bladeThickness)+1 ; x++) {
		  g.drawLine(0,(int)(bladeThickness*x),panelMiddle.getWidth(),(int)(bladeThickness*x));
		  g.drawLine((int)(bladeThickness*x),0,(int)(bladeThickness*x),panelMiddle.getHeight());
		  }
		 
		 
		 
		 int oldY = 0;
		 ArrayList<Rectangle> DrawRect = new ArrayList<Rectangle>();
		 for(Wood W : Boards)
		 {
			 
			 g.setColor(Color.black);
			 g.fillRect((int)(0-bladeThickness), 0-(int)(bladeThickness+oldY),(int)(W.GetWidth() + (2*bladeThickness)),(int)(W.GetHeight()+ (2*bladeThickness))); 
			 
			  g.setColor(Color.magenta);
			  DrawRect.add(new Rectangle(0, 0 +oldY,(int)W.GetWidth(),(int)W.GetHeight()));
			   g.fillRect(0, 0 +oldY,(int)W.GetWidth(),(int)W.GetHeight());
			   
	
			   
			   
			  // g.setColor(Color.blue);
			 //  g2d.setStroke(new BasicStroke((int)bladeThickness));
			//	g.drawRect(0-((int)bladeThickness/2), 0 +oldY - ((int)bladeThickness/2),(int)W.GetLength() + ( (int)bladeThickness),(int)W.GetWidth()+((int)bladeThickness));
			//	g.fillRect(0-(int)bladeThickness, 0-(int)bladeThickness+oldY,(int)W.GetLength() + ( (int)bladeThickness),(int)W.GetWidth()+((int)bladeThickness));   
		        
			   oldY+=(int)(W.GetHeight()+ bladeThickness);
			   
			 
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
					 g.fillRect((int)(R.getX()-bladeThickness), (int)(R.getY()-bladeThickness+oldY),(int)(W.GetWidth() + ( 2*bladeThickness)),(int)(W.GetHeight()+(2*bladeThickness)));
					 g.setColor(Color.green);
					  //DrawRect.add(new Rectangle((int)R.getX(), (int)R.getY() +oldY,(int)W.GetWidth(),(int)W.GetHeight()));
					 Rectangle Name = new Rectangle((int)R.getX(), (int)R.getY() +oldY,(int)W.GetWidth(),(int)W.GetHeight());
					   g.fillRect((int)R.getX(), (int)R.getY() +oldY,(int)W.GetWidth(),(int)W.GetHeight());
					   g.setColor(Color.black);
					   g.setFont(new Font("Helvetica",0,20));
					   g.drawString(W.GetName(), (int)Name.getCenterX(), (int)Name.getCenterY()-10);
					   
					   oldY+=(int)(W.GetHeight()+ bladeThickness);
					   block = DrawRect.indexOf(R);
					  
				 }
			 }
			 
			if( found)
				 DrawRect.remove(block);
		 }
		 
		 
		 
	/*	  g2d.setStroke(new BasicStroke(1)); 
		  g.setColor(Color.BLACK);
		  for(int x = 1; x<(int)(panelMiddle.getWidth()/(int)bladeThickness)+1 ; x++) {
		  g.drawLine(0,(int)bladeThickness*x,panelMiddle.getWidth(),(int)bladeThickness*x);
		  g.drawLine((int)bladeThickness*x,0,(int)bladeThickness*x,panelMiddle.getHeight());
		  	}
	 */
		  
		 
		 
		
		 
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
        new CycleGUI();
    }

    
}