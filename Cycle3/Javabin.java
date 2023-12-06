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
		        	
	        		board.add(run); //populates the board list
	        		d++;
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
	        
	       
	         // runs sorting
	        
	        do { // will run through all pieces NOTE SAME LOGIC ERROR AS THE MULTI BOARD I THINK, SOMETHING TO DO WITH IT GOING TO BOTH BOARDS
	        	
	        	do { //will run through each stock sheet
	        		int traker=0;
	        		System.out.println(traker);
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
	        					pieceArea = (int)colist.get(i).getXsize() * (int)colist.get(i).getYsize();
	        					totArea= totArea + pieceArea;
	        					
	        				}
	        			}
	        			
	        		}
	        				
	        				remaing = boardArea - totArea;
	        				
	        				
	        				if( remaing >0 && pieceArea <= remaing) { //sees if there is space left on board and if the current piece will fit
	        					
	        					ArrayList<CoordMaker> placement = new ArrayList<CoordMaker>(); 
	        					
	        					
	        					
	        					if(firsttime == true ) // NOTE THIS ALWAYS RUNS
	        					{ //if there has been no pieces placed make the first one 0,0
	        						x= 0;
	        						y=0;
	        						canplace = true;
	        						CoordMaker cut = new CoordMaker(pieces.get(count).GetName(),  board.get(plank).GetName(), x, y, (int) pieces.get(count).GetWidth(),(int) pieces.get(count).GetHeight() );
	        						colist.add(cut);
        							placed = true;
        							System.out.println("first piece in " + board.get(plank).GetName() +cut.getPiece() );
        							
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
	        								int backx =  (int) (lol.getX() - InnerPanel.kerfThickness);
	        								int fully = (int) (lol.getY()+ lol.getYsize() + InnerPanel.kerfThickness);
	        								int backy = (int) (lol.getY()- InnerPanel.kerfThickness);
	        								
	        								if(x < fullx &&  y < fully ) { //NOTE : ADD THE OVERHANG CHECK
	        									canplace = false;
	        									
	        								}
	        								else {
	        									canplace = true;
	        									placed = true;
	        								}
	        							}
	        						
	        						
	        						
	        							
	        							if(canplace == false) {
	        								
	        								x= (int) (placement.get(q).getXsize() + placement.get(q).getX() + InnerPanel.kerfThickness); // if collisions move to the end of the placed piece
	        								
	        								
	        								q++;
	        								
	        								temp++; //NOTE THIS IS THE PROBLEM IT'S NEEDED TO CONTROL THE AMOUNT OF PIECES BUT ALSO STOPS THE PIECES BEFORE A DROP CAN OCCUR!!!!!
	        								drop++;
	        								System.out.println("q value " +q); 
	        							}
	        								if (pieces.get(count).GetWidth() + x > board.get(plank).GetWidth()) { // if the new placement goes outside the side border move down 1
	        									
	        									
	        								y= y+1;
	        								x=0;
	        								q=0;
	        								System.out.println("q is reset " +q);
	        								
	        								

	        								}
	        							if (y+ pieces.get(count).GetHeight() > board.get(plank).GetHeight()) {
	        								
        									notpos = true;
        									int test = (int) (y+ pieces.get(count).GetHeight());
        									System.out.println(test +" " + board.get(plank).GetHeight() +" " + notpos + " " + plank);
        								}
        								if(q > placement.size()&& placed == false) {
        									notpos = true;
        									
        								}
	        							
	        							
        								
	        							}while (canplace == false && notpos == false);
	        						q=0;
	        						if(notpos == true && y+ pieces.get(count).GetHeight() > board.get(plank).GetHeight() ) {
	        						
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
	        			
	        		
	        		if(isSpace == false || notpos == true) {
	        			plank++;
	        		}
	        		
	        	}while(plank < board.size() && placed == false);
	        	System.out.println(colist.size());
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

	    	   
	    	   
}
