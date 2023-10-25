import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class Algorithm {
	static ArrayList<Wood> Boards  = new ArrayList<Wood>();
	static ArrayList<Wood> Pieces  = new ArrayList<Wood>();
	
	public static void DrawAlg(){
		ArrayList<Wood> hold = new ArrayList<Wood>();
		//hold = Javabin.alg(List);
		MiddlePanel.panelMiddle.removeAll();
		JPanel ty = new JPanel(){
 		   @Override
 		   public void paint (Graphics g){
 			  g.setColor(Color.LIGHT_GRAY);
 			  g.fillRect(0,0,this.getWidth(),this.getHeight());
 			  Canvas(g);
 		   }
 	   };

 	  MiddlePanel.panelMiddle.add(ty);
	}
	
	public static void Canvas(Graphics g){
		 Graphics2D g2d = (Graphics2D) g;
		 
		 g2d.setStroke(new BasicStroke(1)); 
		 g.setColor(Color.BLACK);
		 for(int x = 1; x<(int)(MiddlePanel.panelMiddle.getWidth()/InnerPanel.kerfThickness)+1 ; x++){
			 g.drawLine(0,(int)(InnerPanel.kerfThickness*x),MiddlePanel.panelMiddle.getWidth(),(int)(InnerPanel.kerfThickness*x));
			 g.drawLine((int)(InnerPanel.kerfThickness*x),0,(int)(InnerPanel.kerfThickness*x),MiddlePanel.panelMiddle.getHeight());
		 }
		 
		 
		 
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
			 DrawRect.add(new Rectangle(0, 0 +oldY,(int)W.GetWidth(),(int)W.GetHeight()));
			 g.fillRect(0, 0 +oldY,(int)W.GetWidth(),(int)W.GetHeight());
			   
			 oldY+=(int)(W.GetHeight()+ InnerPanel.kerfThickness);
		 }
		 		 
		 boolean found = false;
		 int block;
		 for(Wood W : Pieces){
			 block = -1;
			 found = false;
			 for (Rectangle R : DrawRect){
				 oldY = 0;
				 if(R.getWidth() > W.GetWidth() && R.getHeight()>W.GetHeight() && !found)
				 {
					 found = true;
					 g.setColor(Color.blue);
					 g.fillRect((int)(R.getX()-InnerPanel.kerfThickness), (int)(R.getY()-InnerPanel.kerfThickness+oldY),(int)(W.GetWidth() + ( 2*InnerPanel.kerfThickness)),(int)(W.GetHeight()+(2*InnerPanel.kerfThickness)));
					 g.setColor(Color.green);
					 //DrawRect.add(new Rectangle((int)R.getX(), (int)R.getY() +oldY,(int)W.GetWidth(),(int)W.GetHeight()));
					 Rectangle Name = new Rectangle((int)R.getX(), (int)R.getY() +oldY,(int)W.GetWidth(),(int)W.GetHeight());
					 g.fillRect((int)R.getX(), (int)R.getY() +oldY,(int)W.GetWidth(),(int)W.GetHeight());
					   
					 //Display Label
					 g.setColor(Color.black);
					 g.setFont(new Font("Helvetica",0,(int)InnerPanel.kerfThickness));
					 FontMetrics fontMetrics = g.getFontMetrics(g.getFont());
					 int PW = fontMetrics.stringWidth(W.GetName());
					 g.drawString(W.GetName(), (int)(Name.getCenterX() - (int)(PW/2)), (int)(Name.getCenterY() +(int)(InnerPanel.kerfThickness/3)));
					   
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
		ArrayList<Wood> List = CutList.GetWood();			
		CreateTable(List);
	}
	

	private static void CreateTable(ArrayList<Wood> List){
		InnerPanel.innerPanel.removeAll();
		if (!Boards.isEmpty()){
			Boards.clear();
		}
		if (!Pieces.isEmpty()){
				Pieces.clear();
		}
		
		JLabel length = new JLabel("Height");
        JLabel width = new JLabel("Width");
        JLabel quantity = new JLabel("Quantity     ");
        JLabel label0 = new JLabel("Label");
        
        try {
			InnerPanel.innerPanel.add(length);
		} catch (Exception e) {
			e.printStackTrace();
		}
        InnerPanel.innerPanel.add(width);
        InnerPanel.innerPanel.add(quantity);
        InnerPanel.innerPanel.add(label0);
        
		InnerPanel.innerSheetsPanel.removeAll();

        
        JLabel length1 = new JLabel("Height");
		JLabel width1 = new JLabel("Width");
		JLabel quantity1 = new JLabel("Quantity     ");
        JLabel label1 = new JLabel("Label");
        
        InnerPanel.innerSheetsPanel.add(length1);
        InnerPanel.innerSheetsPanel.add(width1);
		InnerPanel.innerSheetsPanel.add(quantity1);
        InnerPanel.innerSheetsPanel.add(label1);
        
        
	
        for(Wood W : List){
			if(W.GetWoodtype().equals("Piece")){
				//System.out.println("Piece found"); 
		        JTextArea label= new JTextArea(W.GetHeight()+"");
		        label.setEditable(false);
		        InnerPanel.innerPanel.add(label);
		        label= new JTextArea(W.GetWidth()+"");
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
	        	JTextArea label= new JTextArea(W.GetHeight()+"");
	        	label.setEditable(false);
	        	InnerPanel.innerSheetsPanel.add(label);
	        	label= new JTextArea(W.GetWidth()+"");
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
