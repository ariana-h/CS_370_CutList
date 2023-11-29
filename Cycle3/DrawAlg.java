import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

public class DrawAlg {
	static int k = 0;
	static boolean lock =false;
	public static void dalg(ArrayList<Wood> Board, ArrayList<CoordMaker> colist) {
		
		do {
	JPanel rupart = new JPanel() {
		  int by=0;
		@Override
		   public void paint (Graphics g) {
			    if (lock == false) {
			    	System.out.println("K is: " +k);
			  System.out.println("if this comes up multiple time we're fucked");
			  
			  g.setColor(Color.black);
			  g.fillRect(0,by, (int) (Board.get(k-3).GetWidth() +InnerPanel.kerfThickness) , (int) (Board.get(k-3).GetHeight() + InnerPanel.kerfThickness));
			  g.setColor(Color.GREEN);
			  g.fillRect(0,by, (int) Board.get(k-3).GetWidth(), (int) Board.get(k-3).GetHeight());
			  by = (int) (by+ Board.get(k-3).GetHeight()+ 30);
			  lock = true;
			    }
			  
			   for(int j=0; j< colist.size(); j++) {
				   if(colist.get(j).getBase() == Board.get(k-3).GetWoodtype() ) {
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
		k++;
	   }while (k < Board.size() );
		
	}
	
}


