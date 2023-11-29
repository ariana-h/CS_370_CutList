import java.util.ArrayList;
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

public class Javabin {
            

	       static  ArrayList<Wood> pieces = new ArrayList<Wood>();
	       static  ArrayList<Wood> board = new ArrayList<Wood>(); 
	       static ArrayList<Wood> inhere;
	       static ArrayList<CoordMaker> colist = new ArrayList<CoordMaker>();
	        
	        static int i, c; 
	        static double cw, ch, bw, bh;
	        
	   public static ArrayList<CoordMaker> alg (ArrayList<Wood> List) {     

		  inhere = List; //populates a list for the algorithm
		  
	       
	
	        
    // separates the list into the "cuts" and board   
	        int d=0, val = 0;
	        
	        for (i = 0; i< inhere.size(); i++) {
	        	
	        	

	        	if(inhere.get(i).GetWoodtype().contains("Board"))
	        			{
	        		
		        	bh= inhere.get(i).GetHeight();
		        	bw= inhere.get(i).GetWidth();
		        	for(int z= 0; z< inhere.get(i).GetAmount(); z++) {
		        	Wood run = new Wood("Sheet " + d, inhere.get(i).GetGrain(), bh,  bw, inhere.get(i).GetAmount());
		        	System.out.println(run.GetWoodtype() +" "+ run.GetWidth() + " " + run.GetHeight());
	        		board.add(run); //populates the board list
	        		d++;
		        	}
	        	}
	        	else {
		        	ch = inhere.get(i).GetHeight();
		        	cw = inhere.get(i).GetWidth();
		        	
		        	Wood run = new Wood(inhere.get(i).GetWoodtype(), inhere.get(i).GetGrain(), ch,  cw, inhere.get(i).GetAmount());
		        	for(int t= 0; t < run.GetAmount(); t++) {
		        		System.out.println(cw + " " + ch);
		        		pieces.add(run);
		        		
		        	}
	        	}
	        }
	        
	        
	       // make();   CHECK IT TO SEE IF IT WAITS TO THE END OF THE LIST BEFORE TRYING TO PLACE
	        int count = 0; 
	        int plank = 0;
	        int boardArea, pieceArea= 0, remaing,x =0 ,y = 0, temp= 0, q =0, xtra = 0, by =0;
	        int totArea = 0;
	        boolean isSpace = true;
	        boolean canplace = false;
	        boolean firsttime = true;
	        boolean notpos = false;
	        boolean placed = false;
	         // runs sorting
	        
	        do { // will run through all pieces
	        	
	        	do { //will run through each stock sheet
	        		
	        		boardArea = (int)board.get(plank).GetHeight() * (int)board.get(plank).GetWidth();
	        		if(colist.size() > 0) 
	        		{
	        			for (i=0; i< colist.size(); i++) 
	        			{
	        				// checks to see the pieces within current board 
	        				
	        				if(colist.get(i).getBase() == board.get(plank).GetWoodtype() ) 
	        				{
	        					pieceArea = (int)colist.get(i).getXsize() * (int)colist.get(i).getYsize();
	        					totArea= totArea + pieceArea;
	        					firsttime = false;
	        				}
	        			}
	        			
	        		}
	        				
	        				remaing = boardArea - totArea;
	        				
	        				
	        				if( remaing >0 && pieceArea <= remaing) { //sees if there is space left on board and if the current piece will fit
	        					
	        					ArrayList<CoordMaker> placement = new ArrayList<CoordMaker>(); 
	        					
	        					
	        					
	        					if(firsttime == true ) 
	        					{ //if there has been no pieces placed make the first one 0,0
	        						x= 0;
	        						y=0;
	        						canplace = true;
	        						CoordMaker cut = new CoordMaker(pieces.get(count).GetName(), ((ArrayList<Wood>) board).get(plank).GetWoodtype(), x, y, (int) pieces.get(count).GetWidth(),(int) pieces.get(count).GetHeight() );
	        						colist.add(cut);
        							placement.add(cut);
        							
	        					}
	        					else { //after the first piece we now move to the actual algorithm
	        						
	        						for (int a=0; a < colist.size(); a++) {
	        							if(colist.get(a).getBase() == board.get(plank).GetWoodtype() ) {
	        								placement.add(colist.get(a));
	        							}
	        						}
	        					
	        					 // checks if the  unplaced piece is colliding with any already placed piece
	        						do {
	        							for(CoordMaker lol : placement) {
	        								if(x < lol.getXsize() + lol.getX() +InnerPanel.kerfThickness && y < lol.getY()+ lol.getYsize() +InnerPanel.kerfThickness) {
	        									canplace = false;
	        								}
	        								else {
	        									canplace = true;
	        								}
	        							}
	        						
	        						
	        						
	        							
	        							if(canplace == false) {
	        								
	        								x= (int) (placement.get(q).getXsize() + placement.get(q).getX() + InnerPanel.kerfThickness); // if collisions move to the end of the placed piece
	        								
	        								System.out.println("x is: " + x);
	        								q++;
	        								
	        								temp++;
	        								System.out.println(" times here: " + temp); 
	        								if (pieces.get(count).GetWidth() + x > board.get(plank).GetWidth()) {
	        									System.out.println("oob");
	        								
	        								y= y+1;
	        								x=0;
	        								q=0;
	        								if (y+ pieces.get(count).GetHeight() > board.get(plank).GetHeight()) {
	        									notpos = true;
	        								}
	        								if(temp > placement.size()) {
	        									notpos = true;
	        								}
	        								}
	        								
	        								
	        							
	        								
	        								
	        							
	        							}
	        							}while (canplace == false && notpos == false);
	        						
	        						
	        						if (canplace == true){
	        							CoordMaker cut = new CoordMaker(pieces.get(count).GetName(), ((ArrayList<Wood>) board).get(plank).GetWoodtype(), x, y, (int) pieces.get(count).GetWidth(),(int) pieces.get(count).GetHeight() );
	        							colist.add(cut);
	        							
	        							
	        							break;
	        						}
	        					
	        					
	        					
	        					
	        				}
	        				
	        			}
	        				
	        			
	        		
	        		if(isSpace == false) {
	        			plank++;
	        		}
	        		;
	        	}while(plank < board.size());
	        	System.out.println(colist.size());
	        	count++;
	        	plank=0;
	        	totArea=0;
	        	remaing = 0;
	        	isSpace= true;
	        	canplace = false;
	        	notpos = false;

	        } while(count < pieces.size());
	      
	       
	      for(int p =0; p< colist.size(); p++) {
	    	  System.out.println(colist.get(p).getBase() + ", " + colist.get(p).getX() + ", " + colist.get(p).getY());
	    	  
	      }
	      
	      for(int w=0; w< board.size();w++) {
	      DrawAlg.dalg(board, colist);
	     
	      }
	        return colist;
	       
	        
	        
}
	   /*static public JPanel make() {
			MiddlePanel.panelMiddle.removeAll();
			JPanel ty = new JPanel(){
	 		   @Override
	 		   public void paint (Graphics g){
	 			  g.setColor(Color.BLUE);
	 			  g.fillRect(0,0, (int) board.get(0).GetWidth(), (int) board.get(0).GetHeight() );
	 			  Algorithm.Canvas(g);
	 		   }
	 	   };
	 	  
			return ty;
	   }
	     */   
	       static public ArrayList<Wood> results() {
	        	//will first create a rectangle from board and draw it
	    	   
	    	   return board;
	    	   
	    	   
	    	   
	       }
	      
	    	   
	    	   
}



