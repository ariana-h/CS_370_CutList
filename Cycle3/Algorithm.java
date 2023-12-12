import java.awt.*;
import java.awt.geom.AffineTransform;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.*;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;


public class Algorithm {
	static ArrayList<Wood> Boards  = new ArrayList<Wood>();
	static ArrayList<Wood> Pieces  = new ArrayList<Wood>();
	static boolean grid , Label , Measure, calc, FileRead = false;
	static JPanel ty;
	
	public static int UsedArea , TotalArea;
	public static int TotBoard , RemBoard;
	public static ArrayList<Wood> List;
	
	//Haydens Alg
	static ArrayList<CoordMaker> colist = new ArrayList<CoordMaker>();
	public static double scale = 1.0;
	
	
	@SuppressWarnings("serial")
	public static void DrawAlg(){

		MiddlePanel.panelMiddle.removeAll();
		 ty = new JPanel(){
 		   @Override
 		   public void paint (Graphics g){
 			   
 			  g.setColor(Color.LIGHT_GRAY);
 			  g.fillRect(0,0,this.getWidth(),this.getHeight());	 
 				 Canvas(g);

 			  if(calc)
 			  { 
 			 InnerPanel.UsedStock.setText("Total Used Stock: " + (RemBoard) + "/" + TotBoard);
 			 InnerPanel.UsedArea.setText("Total Used Area: "+ UsedArea + " / " + (int)((((double)(UsedArea)/TotalArea))*100)+"%" );
 		  	 InnerPanel.WastedArea.setText("Total Wasted Area: "+(TotalArea - UsedArea)+ " / " + (int)((((double)(TotalArea-UsedArea)/TotalArea))*100)+"%");
 		  	 calc = false;
 			  }
 		   }   
 	   };
 	  MiddlePanel.panelMiddle.add(ty);
 	  TotalArea=UsedArea = 0;
	}
	
	public static void Canvas(Graphics g){
		
		if(grid)
		{
			g.setColor(Color.BLACK);
			 for(int x = 1; x<(int)(MiddlePanel.panelMiddle.getWidth()/10)+1 ; x++){
				 g.drawLine(0,(int)(10*x),MiddlePanel.panelMiddle.getWidth(),(int)(10*x));
				 g.drawLine((int)(10*x),0,(int)(10*x),MiddlePanel.panelMiddle.getHeight());
			 }
		}
		
		FontMetrics fontMetrics = g.getFontMetrics(g.getFont());
		 Graphics2D g2d = (Graphics2D) g;
		 
		 g2d.setStroke(new BasicStroke(1)); 
		 
		 
		 int oldY = 0;
		 TotBoard=0;
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
			 TotBoard++;
			 TotalArea += Stock.height * Stock.width;
 
			 oldY+=(int)(W.GetHeight()+ InnerPanel.kerfThickness);
		 }
		  
		 RemBoard = 0;
		 boolean[] skip = new boolean[Pieces.size()];
		 for(int i = 0; i< skip.length ; i++)
		 		skip[i] = false;
	for(int rect = 0; rect<DrawRect.size();rect++)
	{
		Rectangle R = DrawRect.get(rect);
		int MaxX = (int)R.getWidth();
		int MaxY= (int)R.getHeight();
		int minX=0, tempMinx=0, minY = 0;
		boolean used = false;
		for (int t = 0; t<Pieces.size();t++)
		{
			Wood w=Pieces.get(t);
			if(!skip[t])
			{	
				
			if(((MaxY>=(minY+InnerPanel.kerfThickness+w.GetHeight()))||(MaxY==(minY+w.GetHeight())))&&(minX+w.GetWidth())<=MaxX&&!skip[t])
			{
				//System.out.println("Piece Fits");
				if(MaxX>=(w.GetWidth()+InnerPanel.kerfThickness)&&(w.GetWidth()+InnerPanel.kerfThickness)>tempMinx&&(w.GetWidth()+InnerPanel.kerfThickness)>=minX)
					tempMinx =(int)(w.GetWidth()+InnerPanel.kerfThickness);
				
				
				
				used = true;	
				g.setColor(Color.blue);
				 g.fillRect((int)(R.getX()-InnerPanel.kerfThickness+minX), (int)(R.getY()-InnerPanel.kerfThickness+minY),(int)(w.GetWidth() + ( 2*InnerPanel.kerfThickness)),(int)(w.GetHeight()+(2*InnerPanel.kerfThickness)));
				 g.setColor(Color.green);
			//	 System.out.println("Trial: "+t+" Min: "+ minY + " Max: "+MaxY + "\n Drawing in rect:" +rect + " X: "+(R.getX()+minX)+" Y:" +(R.getY() +minY)+"\n");
				 //DrawRect.add(new Rectangle((int)R.getX(), (int)R.getY() +oldY,(int)W.GetWidth(),(int)W.GetHeight()));
				 Rectangle Name = new Rectangle((int)R.getX()+minX, (int)R.getY() +minY,(int)w.GetWidth(),(int)w.GetHeight());
				 g.fillRect((int)R.getX()+minX, (int)R.getY() +minY,(int)w.GetWidth(),(int)w.GetHeight());
				 
				 UsedArea += Name.width * Name.height;
				   
				 //Display Label
				 if(Label)
				 {
				 g.setColor(Color.black);
				 g.setFont(new Font("Helvetica",Font.BOLD,10));
				 int PW = fontMetrics.stringWidth(w.GetName());
				 g.drawString(w.GetName(), (int)(Name.getCenterX() - (int)(PW/2)), (int)(Name.getCenterY() +(int)(InnerPanel.kerfThickness/2)));
				 }
				 if (Measure)
				 { 
					 g.setColor(Color.BLACK);
					 g.setFont(new Font("Helvetica",Font.BOLD,10));
					 int PW = fontMetrics.stringWidth(String.valueOf(Buttons.BLT.format(w.GetWidth())));
					 
					g.drawString(String.valueOf(Buttons.BLT.format(w.GetWidth())), (int)(Name.getCenterX() - (int)(PW/2)), (int)(Name.getCenterY()+ (w.GetHeight()/2)));
					
                    // Calculate the center of the panel
					int centerX = (int)Name.getWidth()/2;
					int centerY = (int)Name.getHeight()/2;
					
					int PW1 = fontMetrics.stringWidth(String.valueOf(Buttons.BLT.format(w.GetHeight())));
					int PW2 = fontMetrics.getHeight();
					
					int textX = (int)(centerX + PW1*2);
					int textY = (int)(centerY + PW2*2);
				

                    // Rotate the text by -90 degrees at the calculated position
                    AffineTransform oldTransform = g2d.getTransform();
                   // g2d.translate(centerX, centerY);
                    g2d.rotate(Math.toRadians(-90),textX , textY);
                    g2d.drawString(String.valueOf(Buttons.BLT.format(w.GetHeight())),textX,textY);

                    // Reset the transformation
                    g2d.setTransform(oldTransform);
                    
				 }  
				
				
				
				
				minY += InnerPanel.kerfThickness+w.GetWidth();
				skip[t] = true;
			}
			
			
			
			if(MaxY<=minY || minY+w.GetHeight()>=MaxY)
			{
				if(tempMinx==0)
					t=Pieces.size();
				
				//System.out.println("\n Piece Can no longer fit down. Shifting over: "+ tempMinx + "\n");
				minY = 0;
				minX+=tempMinx;
				tempMinx=0;
			}
				
			}		
		}
		if(used)
			RemBoard++;
		
	}
	
	//End of alg	 
		 
	
	}
	
	
	public static void CutListAlgorithm(String name) throws IOException, EncryptedDocumentException, InvalidFormatException{
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
				//System.out.println("Piece found"); `
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
		        int loop = W.GetAmount();
	        	for(int x = 1; x<= loop; x++)
	        	{
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
	        	int loop = W.GetAmount();
	        	for(int x = 1; x<= loop; x++)
	        	{
	        		Boards.add(W);
	        	}
			}
		}  
	}
	
	public static void zoomIn() {
        scale *= 1.02;
        for(int x = 0 ; x < Boards.size(); x++)
        {
        	Wood w = Boards.get(x);
        	w.SetHeight(w.GetHeight()*scale);
        	w.SetWidth(w.GetWidth()*scale);
        	x+=w.GetAmount()-1;
        }
        for(int x = 0 ; x < Pieces.size(); x++)
        {
        	Wood w = Pieces.get(x);
        	w.SetHeight(w.GetHeight()*scale);
        	w.SetWidth(w.GetWidth()*scale);
        	x+=w.GetAmount()-1;
        } 

        ty.repaint();
    }

    public static void zoomOut() {
        for(int x = 0 ; x < Boards.size(); x++)
        {
        	Wood w = Boards.get(x);
        	w.SetHeight(w.GetHeight()*scale);
        	w.SetWidth(w.GetWidth()*scale);
        	x+=w.GetAmount()-1;
        }
        for(int x = 0 ; x < Pieces.size(); x++)
        {
        	Wood w = Pieces.get(x);
        	w.SetHeight(w.GetHeight()*scale);
        	w.SetWidth(w.GetWidth()*scale);
        	x+=w.GetAmount()-1;
        }
       
        ty.repaint();
    }

}