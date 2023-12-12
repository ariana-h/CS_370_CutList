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
		        	Wood run = new Wood(inhere.get(i).GetWoodtype(), inhere.get(i).GetGrain(), bh,  bw, inhere.get(i).GetAmount(), "Sheet " + d);
		        	d++;
	        		board.add(run); //populates the board list
	        		
		        	}
	        	}
	        	else {
		        	ch = inhere.get(i).GetHeight();
		        	cw = inhere.get(i).GetWidth();
		        	
		        	
		        	Wood run = new Wood(inhere.get(i).GetWoodtype(), inhere.get(i).GetGrain(), ch,  cw, inhere.get(i).GetAmount(), inhere.get(i).GetName());
		        	for(int t= 0; t < inhere.get(i).GetAmount(); t++) {
		        		
		        		pieces.add(run);
		        		
		        	}
	        	}
	        }
	        
	        
	          
	        int count = 0; 
	        int plank = 0;
	        int boardArea, pieceArea= 0, remaing,x =0 ,y = 0, temp= 0, q =0;
	        int totArea = 0;
	        boolean isSpace = true;
	        boolean canplace = false;
	        boolean firsttime = true;
	        boolean notpos = false;
	        boolean placed = false;
	        boolean cantplace = false;
	        
	       
	         // runs sorting
	        
	        do { // will run through all pieces 
	        	
	        	do { //will run through each stock sheet
	        		
	        		totArea=0;
		        	remaing = 0;
	        		firsttime = true;
	        		
	        		int traker=0;
	        		
	        		boardArea = (int)board.get(plank).GetHeight() * (int)board.get(plank).GetWidth();
	        		for (CoordMaker col : colist) {
	        			if (col.getBase().equals(board.get(plank).GetName()) ) {
	        				traker++;
	        				firsttime = false;
	        			}
	        		}
	        		
	        		if(traker > 0) 
	        		{
	        			for (i=0; i< colist.size(); i++) 
	        			{
	        				// checks to see the pieces within current board 
	        				
	        				if(colist.get(i).getBase().equals(board.get(plank).GetName())  ) 
	        				{
	        					pieceArea = (int)(colist.get(i).getXsize()+ InnerPanel.kerfThickness) * (int)(colist.get(i).getYsize()+ InnerPanel.kerfThickness);
	        					totArea= totArea + pieceArea;
	        					
	        				}
	        			}
	        			
	        		}
	        				
	        				remaing = boardArea - totArea;
	        				
	        				
	        				if( remaing >0 && pieceArea <= remaing && pieces.get(count).GetWidth() <= board.get(plank).GetWidth() && pieces.get(count).GetHeight() <= board.get(plank).GetHeight() ) { //sees if there is space left on board and if the current piece will fit
	        					isSpace = true;
	        					ArrayList<CoordMaker> placement = new ArrayList<CoordMaker>(); 
	        					
	        					
	        					
	        					if(firsttime == true ) 
	        					{ //if there has been no pieces placed make the first one 0,0
	        						x= 0;
	        						y=0;
	        						canplace = true;
	        						CoordMaker cut = new CoordMaker(pieces.get(count).GetName(),  board.get(plank).GetName(), x, y, (int) pieces.get(count).GetWidth(),(int) pieces.get(count).GetHeight() );
	        						colist.add(cut);
        							placed = true;
        							
        							
	        					}
	        					else if (firsttime ==false) { //after the first piece we now move to the actual algorithm
	        						
	        						for (int a=0; a < colist.size(); a++) {
	        							if(colist.get(a).getBase().equals(board.get(plank).GetName())  ) {
	        								placement.add(colist.get(a));
	        								
	        								
	        							}
	        						}
	        					
	        					 // checks if the  unplaced piece is colliding with any already placed piece
	        						do {
	        							
	        							int drop=0;
	        							for(CoordMaker lol : placement) {
	        								int fullx= (int) (lol.getXsize() + lol.getX() + InnerPanel.kerfThickness);
	        								
	        								int fully = (int) (lol.getY()+ lol.getYsize() + InnerPanel.kerfThickness);
	        								
	        								
	        								if(x < fullx  && y < fully ) { 
	        									canplace = false;
	        									placed= false;
	        									
	        								}
	        								else {
	        									
	        									canplace = true;
	        									placed = true;
	        								}
	        							}
	        						
	        						
	        						
	        							
	        							if(canplace == false) {
	        								
	        								x= (int) (placement.get(q).getXsize() + placement.get(q).getX() + InnerPanel.kerfThickness); // if collisions move to the end of the placed piece
	        								
	        								
	        								q++;
	        								
	        								
	        								drop++;
	        								
	        							}
	        								if (pieces.get(count).GetWidth() + x > board.get(plank).GetWidth()) { // if the new placement goes outside the side border move down 1
	        									
	        									
	        								y= y+1;
	        								x=0;
	        								q=0;
	        								
	        								
	        								

	        								}
	        							if (y+ pieces.get(count).GetHeight() > board.get(plank).GetHeight()) {
	        								
        									notpos = true;
        									
        								}
        								if(q > placement.size()&& placed == false) {
        									notpos = true;
        									
        								}
	        							
	        							
        								
	        							}while (canplace == false && notpos == false);
	        						q=0;
	        						if(notpos == true && y+ pieces.get(count).GetHeight() > board.get(plank).GetHeight() ) {
	        							
	        						plank++;
	        						}
	        						if (canplace == true){
	        							CoordMaker cut = new CoordMaker(pieces.get(count).GetName(),  board.get(plank).GetName(), x, y, (int) pieces.get(count).GetWidth(),(int) pieces.get(count).GetHeight() );
	        							colist.add(cut);
	        							
	        							
	        						}
	        					
	        					
	        					
	        					
	        				}
	        				
	        			}
	        				else {
	        					isSpace = false;
	        				}
	        			
	        		
	        		if(isSpace == false) {
	        			plank++;
	        		}
	        		
	        		
	        		
	        		
	        	}while(plank < board.size() && placed == false);
	        	
	        	count++;
	        	plank=0;
	        	totArea=0;
	        	remaing = 0;
	        	isSpace= true;
	        	canplace = false;
	        	notpos = false;
	        	placed = false;
	        	

	        } while(count < pieces.size());
	      
	       
	      
	        return colist;
	       
	        
	        
}
	   
	

	    	   
	    	   
}
