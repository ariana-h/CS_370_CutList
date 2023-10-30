import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class Javabin {
            

	       static  ArrayList<Wood> pieces = new ArrayList<Wood>();
	       static  ArrayList<Wood> board = new ArrayList<Wood>(); 
	       static ArrayList<Wood> inhere;
	       static ArrayList<CoordMaker> colist = new ArrayList<CoordMaker>();
	        
	        static int i, c; 
	        static double cw, cl, bw, bl;
	        
	   public  static ArrayList<CoordMaker> alg (ArrayList<Wood> List) {     

		  inhere = List; //populates a list for the algorithm
		  
	       
	
	        
    // separates the list into the "cuts" and board   
	        int d=0, val = 0;
	        
	        for (i = 0; i< inhere.size(); i++) {
	        	
	        	

	        	if(inhere.get(i).GetWoodtype().contains("Board"))
	        			{
	        		
		        	bl= inhere.get(i).GetLength();
		        	bw= inhere.get(i).GetWidth();
		        	for(int z= 0; z< inhere.get(i).GetAmount(); z++) {
		        	Wood run = new Wood("Sheet " +d, inhere.get(i).GetGrain(), bl,  bw, inhere.get(i).GetAmount());
	        		board.add(run); //populates the board list
		        	}
	        		d++;
	        	}
	        	else {
		        	cl = inhere.get(i).GetLength();
		        	cw = inhere.get(i).GetWidth();
		        	System.out.println();
		        	Wood run = new Wood(inhere.get(i).GetWoodtype(), inhere.get(i).GetGrain(), cl,  cw, inhere.get(i).GetAmount());
		        	for(int t= 1; t > run.GetAmount(); t++) {
		        		pieces.add(run);
		        	}
	        	}
	        }
	        
	        
	        
	        int count = 0; 
	        int plank = 0;
	        int boardArea, pieceArea, remaing,x =0 ,y = 0;
	        int totArea = 0;
	        boolean isSpace = true;
	        boolean canplace = false;
	         // runs sorting
	        
	        do { // will run through all pieces
	        	do { //will run through each stock sheet
	        		boardArea = (int)board.get(plank).GetLength() * (int)board.get(plank).GetWidth();
	        			for (i=0; i< colist.size(); i++) { // checks to see the pieces within current board 
	        				if(colist.get(i).getBase() == board.get(plank).GetWoodtype() ) {
	        					pieceArea = (int)colist.get(i).getXsize() * (int)colist.get(i).getYsize();
	        					totArea= totArea + pieceArea;
	        				}
	        				pieceArea= (int)pieces.get(i).GetLength() * (int)pieces.get(i).GetWidth(); 
	        				remaing = boardArea - totArea;
	        				if( remaing >0 && pieceArea <= remaing) { //sees if there is space left on board and if the current piece will fit
	        					ArrayList<CoordMaker> placement = new ArrayList<CoordMaker>();
	        					if(placement.size() ==0 ) {
	        						x= 0;
	        						y=0;
	        						canplace = true;
	        						CoordMaker cut = new CoordMaker(pieces.get(count).GetWoodtype(), ((ArrayList<Wood>) board).get(plank).GetWoodtype(), x, y, (int) pieces.get(count).GetLength(),(int) pieces.get(count).GetWidth() );
        							colist.add(cut);
	        					}
	        					if (colist.size() > 0) {
	        					for (int e =0; e< colist.size(); e++) {
	        						if(colist.get(i).getBase() == board.get(plank).GetWoodtype()  ) {
	        							placement.add(colist.get(e));
	        						}
	        					}
	        					
	        					for (int q=0; q < placement.size(); q ++) {
	        						for(int h =0; h < board.get(plank).GetWidth(); h++) {
	        							if(x < placement.get(q).getXsize() + placement.get(q).getX() && y <placement.get(q).getY()+ placement.get(q).getYsize()) {
	        								x= placement.get(q).getXsize() + placement.get(q).getX();
	        								canplace = false;
	        								if (pieces.get(count).GetLength() + x > board.get(plank).GetLength()) {
	        								y=h;
	        								x=0;
	        								
	        							}
	        								
	        							}
	        							else {
	        								canplace = true;
	        								break;
	        							}
	        						}
	        						if (canplace == true){
	        							CoordMaker cut = new CoordMaker(pieces.get(count).GetWoodtype(), ((ArrayList<Wood>) board).get(plank).GetWoodtype(), x, y, (int) pieces.get(count).GetLength(),(int) pieces.get(count).GetWidth() );
	        							colist.add(cut);
	        							break;
	        						}
	        					}
	        					
	        					
	        					
	        				}
	        				
	        			}
	        			}	
	        			
	        		if (canplace = true) {
	        						
	        			break;
	        					}
	        		if(isSpace == false) {
	        			plank++;
	        		}
	        	}while(plank < board.size());
	        	count++;
	        	plank=0;
	        	totArea=0;
	        	remaing = 0;
	        	isSpace= true;
	        } while(count < pieces.size());
	      
	       
	      
	            
	        return colist;
	        
	        
}
	        
	       static public ArrayList<Wood> results() {
	        	//will first create a rectangle from board and draw it
	    	   
	    	   return board;
	       }
	        	
}


