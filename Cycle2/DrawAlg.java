import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

public class DrawAlg {
	static int k = 0;
	public static void dalg(ArrayList<Wood> Board, ArrayList<CoordMaker> colist) {
		
		for( k=0; k< Board.size(); k++) {
	JPanel rupart = new JPanel() {
		  int by=0;

		@Override
		   public void paint (Graphics g) {
			    
			   
			  g.setColor(Color.black);
			  g.fillRect(0,by, (int) (Board.get(k-1).GetWidth() +InnerPanel.kerfThickness) , (int) (Board.get(k-1).GetHeight() + InnerPanel.kerfThickness));
			  g.setColor(Color.GREEN);
			  g.fillRect(0,by, (int) Board.get(k-1).GetWidth(), (int) Board.get(k-1).GetHeight());
			 // by = (int) (by+ Board.get(k-1).GetHeight()+ 30);
			  
			  
			   for(int j=0; j< colist.size(); j++) {
				   if(colist.get(j).getBase() == Board.get(k-1).GetWoodtype() ) {
					   g.setColor(Color.blue);
					   g.fillRect(colist.get(j).getX(), colist.get(j).getY(), colist.get(j).getXsize() + (int) InnerPanel.kerfThickness, colist.get(j).getYsize()+ (int) InnerPanel.kerfThickness);
					   g.setColor(Color.CYAN);
					   g.fillRect(colist.get(j).getX(), colist.get(j).getY(), colist.get(j).getXsize(), colist.get(j).getYsize());
					   
					   
				   }
			   }
			   
			   }
			  /* 
			  int i = 0;
			  for ( i =0; i< size; i++) { 
				  if (d== 0) {
			  g.setColor(Color.magenta);
			 g.fillRect(0, 0,w.get(i), l.get(i));
				 }

		 				  }
				 
			  
			  d++;
			  if (d>= 5) {
				  d=0;
			  }
			  */
			  	 
		   };
		MiddlePanel.panelMiddle.add(rupart);   
	   }
	}
	
}



