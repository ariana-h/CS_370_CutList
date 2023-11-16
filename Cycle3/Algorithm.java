import java.awt.*;
import java.awt.geom.AffineTransform;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.*;


public class Algorithm {
	static ArrayList<Wood> Boards  = new ArrayList<Wood>();
	static ArrayList<Wood> Pieces  = new ArrayList<Wood>();
	static boolean grid , Label , Measure, calc, FileRead = false;
	static JPanel ty = new JPanel();
	
	public static int UsedArea , TotalArea;
	public static ArrayList<Wood> List;
	
	public static void DrawAlg(){

		MiddlePanel.panelMiddle.removeAll();
		 ty = new JPanel(){
 		   @Override
 		   public void paint (Graphics g){
 			   
 			  g.setColor(Color.LIGHT_GRAY);
 			  g.fillRect(0,0,this.getWidth(),this.getHeight());
 			  
 			  if(grid)
 			  {
 			  Grid(g);
 			  }
 			  Canvas(g);
 			  
 			  if(calc)
 			  {
 				 //Javabin.alg(List);
 			 InnerPanel.UsedArea.setText("Total Used Area: "+ UsedArea + " / " + (int)((((double)(UsedArea)/TotalArea))*100)+"%" );
 		  	 InnerPanel.WastedArea.setText("Total Wasted Area: "+ (TotalArea - UsedArea)+ " / " + (int)((((double)(TotalArea-UsedArea)/TotalArea))*100)+"%");
 		  	 calc = false;
 			  }
 		   }   
 	   };
 	  MiddlePanel.panelMiddle.add(ty);
 	  TotalArea=UsedArea = 0;
	}
	
	public static void Grid(Graphics g)
	{
		g.setColor(Color.BLACK);
		 for(int x = 1; x<(int)(MiddlePanel.panelMiddle.getWidth()/10)+1 ; x++){
			 g.drawLine(0,(int)(10*x),MiddlePanel.panelMiddle.getWidth(),(int)(10*x));
			 g.drawLine((int)(InnerPanel.kerfThickness*x),0,(int)(10*x),MiddlePanel.panelMiddle.getHeight());
		 }	
	}
	
	public static void Canvas(Graphics g){
		FontMetrics fontMetrics = g.getFontMetrics(g.getFont());
		 Graphics2D g2d = (Graphics2D) g;
		 
		 g2d.setStroke(new BasicStroke(1)); 
		 
		 
		 
		 
		 int oldY = 0;
		 ArrayList<Rectangle> DrawRect = new ArrayList<Rectangle>();
		 for(Wood W : Boards){
			 /* 
			    g.setColor(Color.orange);
				g2d.setStroke(new BasicStroke((int)bladeThickness));
				g.drawRect(0-((int)bladeThickness/2), 0 +oldY - ((int)bladeThickness/2),(int)W.GetWidth() + ( (int)bladeThickness),(int)W.GetHeight()+((int)bladeThickness));
				g.fillRect(0-(int)bladeThickness, 0-(int)bladeThickness+oldY,(int)W.GetWidth() + ( (int)bladeThickness),(int)W.GetHeight()+((int)bladeThickness));   
		     */
			 g2d.setStroke(new BasicStroke(1));
			 g.setColor(Color.black);
			 g.fillRect((int)(0-InnerPanel.kerfThickness), 0 + oldY - (int)(InnerPanel.kerfThickness),(int)(W.GetWidth() + 
					 (2*InnerPanel.kerfThickness)),(int)(W.GetHeight()+ (2*InnerPanel.kerfThickness))); 
			 
			 g.setColor(Color.magenta);
			 Rectangle Stock = new Rectangle(0, 0 +oldY,(int)W.GetWidth(),(int)W.GetHeight());
			 DrawRect.add(Stock);
			 g.fillRect(0, 0 +oldY,(int)W.GetWidth(),(int)W.GetHeight());
			 
			 TotalArea += Stock.height * Stock.width;
 
			 oldY+=(int)(W.GetHeight()+ InnerPanel.kerfThickness);
		 }
		 		 
		 boolean found = false;
		 int block;
		 for(Wood W : Pieces){
			 block = -1;
			 found = false;
			 for (Rectangle R : DrawRect){
				 oldY = 0;
				 if(R.getWidth() >= W.GetWidth() && R.getHeight()>=W.GetHeight() && !found)
				 {
					 found = true;
					 g.setColor(Color.blue);
					 g.fillRect((int)(R.getX()-InnerPanel.kerfThickness), (int)(R.getY()-InnerPanel.kerfThickness+oldY),(int)(W.GetWidth() + ( 2*InnerPanel.kerfThickness)),(int)(W.GetHeight()+(2*InnerPanel.kerfThickness)));
					 g.setColor(Color.green);
					 //DrawRect.add(new Rectangle((int)R.getX(), (int)R.getY() +oldY,(int)W.GetWidth(),(int)W.GetHeight()));
					 Rectangle Name = new Rectangle((int)R.getX(), (int)R.getY() +oldY,(int)W.GetWidth(),(int)W.GetHeight());
					 g.fillRect((int)R.getX(), (int)R.getY() +oldY,(int)W.GetWidth(),(int)W.GetHeight());
					
					 UsedArea += Name.width * Name.height;
					   
					 //Display Label
					 if(Label)
					 {
					 g.setColor(Color.black);
					 g.setFont(new Font("Helvetica",Font.BOLD,10));
					 int PW = fontMetrics.stringWidth(W.GetName());
					 g.drawString(W.GetName(), (int)(Name.getCenterX() - (int)(PW/2)), (int)(Name.getCenterY() +(int)(InnerPanel.kerfThickness/2)));
					 }
					 if (Measure)
					 {
						int Wid = fontMetrics.stringWidth(String.valueOf(W.GetWidth()));
						int Hit = fontMetrics.stringWidth(String.valueOf(W.GetHeight())); 
						 g.setColor(Color.BLACK);
						 g.setFont(new Font("Helvetica",Font.BOLD,10));
						 int PW = fontMetrics.stringWidth(W.GetName());
						 
						g.drawString(String.valueOf(W.GetWidth()), (int)(Name.getCenterX() - (int)(Wid /2)), (int)(Name.getCenterY()+ (W.GetHeight()/2)));
						
	                    // Calculate the center of the panel
	                    int centerX = (int)(Name.getCenterX() - (int)(PW/2));
	                    int centerY = (int)(Name.getCenterY() +(int)(InnerPanel.kerfThickness/2));
	                    


	                    // Rotate the text by -90 degrees at the calculated position
	                    AffineTransform oldTransform = g2d.getTransform();
	                    g2d.translate(centerX, centerY);
	                    g2d.rotate(-Math.PI / 2);
	                    g2d.drawString(String.valueOf(W.GetHeight()), -(int)((Hit/2)- g.getFont().getSize()/4), (int)(Hit*3) + g.getFont().getSize()/4);

	                    // Reset the transformation
	                    g2d.setTransform(oldTransform);
	                    
						
						
					 }
					   
					 oldY+=(int)(W.GetHeight()+ InnerPanel.kerfThickness);
					 block = DrawRect.indexOf(R);
				 }
			 }
			 
			if(found)
				DrawRect.remove(block);
		 }
		 
	}
	
	
	public static void CutListAlgorithm(String name) throws IOException{
		CutList.main(name);
		List = CutList.GetWood();			
		CreateTable(List);
	}
	

	private static void CreateTable(ArrayList<Wood> List){

	if(!FileRead)
	{
		JLabel length = new JLabel("Height (in)");
        JLabel width = new JLabel("Width (in)");
        JLabel quantity = new JLabel("Quantity     ");
        JLabel label0 = new JLabel("Label");
        
		InnerPanel.innerPanel.add(length);
        InnerPanel.innerPanel.add(width);
        InnerPanel.innerPanel.add(quantity);
        InnerPanel.innerPanel.add(label0);
        

        
        JLabel length1 = new JLabel("Height (in)");
		JLabel width1 = new JLabel("Width (in)");
		JLabel quantity1 = new JLabel("Quantity     ");
        JLabel label1 = new JLabel("Label");
        
        InnerPanel.innerSheetsPanel.add(length1);
        InnerPanel.innerSheetsPanel.add(width1);
		InnerPanel.innerSheetsPanel.add(quantity1);
        InnerPanel.innerSheetsPanel.add(label1); 
	}    
	
        for(Wood W : List){
			if(W.GetWoodtype().equals("Piece")){
				//System.out.println("Piece found"); 
		        JTextArea label= new JTextArea(W.GetHeight()/10+"");
		        label.setEditable(false);
		        InnerPanel.innerPanel.add(label);
		        label= new JTextArea(W.GetWidth()/10+"");
		        label.setEditable(false);
		        InnerPanel.innerPanel.add(label);
		        label= new JTextArea(W.GetAmount()+"");
		        label.setEditable(false);
		        InnerPanel.innerPanel.add(label);
		        label= new JTextArea(W.GetName()+"");
		        label.setEditable(false);
		        InnerPanel.innerPanel.add(label);
		        for(int x = 1; x<= W.GetAmount(); x++){
		        	Pieces.add(W);
		        }
			}
			else{
				//System.out.println("Wood found"); 
	        	JTextArea label= new JTextArea(W.GetHeight()/10+"");
	        	label.setEditable(false);
	        	InnerPanel.innerSheetsPanel.add(label);
	        	label= new JTextArea(W.GetWidth()/10+"");
	        	label.setEditable(false);
	        	InnerPanel.innerSheetsPanel.add(label);
	        	label= new JTextArea(W.GetAmount()+"");
	        	label.setEditable(false);
	        	InnerPanel.innerSheetsPanel.add(label);
	        	label= new JTextArea(W.GetName()+"");
	        	label.setEditable(false);
	        	InnerPanel.innerSheetsPanel.add(label);
	        	for(int x = 1; x<= W.GetAmount(); x++)
	        	{
	        		Boards.add(W);
	        	}
			}
		}  
	}
}