import java.util.ArrayList;


public class Javabin {
            

	       static  ArrayList<Wood> pieces = new ArrayList<Wood>();
	       static  ArrayList<Wood> board = new ArrayList<Wood>(); 
	       static ArrayList<Wood> inhere;
	       static ArrayList<CoordMaker> colist = new ArrayList<CoordMaker>();
	        
	        static int i, c; 
	        static double cw, cl, bw, bl;
	        
	   public static ArrayList<CoordMaker> alg (ArrayList<Wood> List) {     

		  inhere = List; //populates a list for the algorithm
		  
	       
	
	        
    // separates the list into the "cuts" and board   
	        int d=0, val = 0;
	        
	        for (i = 0; i< inhere.size(); i++) {
	        	
	        	

	        	if(inhere.get(i).GetWoodtype().contains("Board"))
	        			{
	        		
		        	bl= inhere.get(i).GetHeight();
		        	bw= inhere.get(i).GetWidth();
		        	for(int z= 0; z< inhere.get(i).GetAmount(); z++) {
		        	Wood run = new Wood("Sheet " + d, inhere.get(i).GetGrain(), bl,  bw, inhere.get(i).GetAmount());
	        		board.add(run); //populates the board list
	        		d++;
		        	}
	        	}
	        	else {
		        	cl = inhere.get(i).GetHeight();
		        	cw = inhere.get(i).GetWidth();
		        	
		        	Wood run = new Wood(inhere.get(i).GetWoodtype(), inhere.get(i).GetGrain(), cl,  cw, inhere.get(i).GetAmount());
		        	for(int t= 0; t < run.GetAmount(); t++) {
		        		pieces.add(run);
		        		
		        	}
	        	}
	        }
	        
	        
	        
	        int count = 0; 
	        int plank = 0;
	        int boardArea, pieceArea= 0, remaing,x =0 ,y = 0, temp= 0;
	        int totArea = 0;
	        boolean isSpace = true;
	        boolean canplace = false;
	        boolean firsttime = true;
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
	        				
	        				}
	        			}
	        			
	        		}
	        				
	        				remaing = boardArea - totArea;
	        				
	        				
	        				if( remaing >0 && pieceArea <= remaing) { //sees if there is space left on board and if the current piece will fit
	        					
	        					ArrayList<CoordMaker> placement = new ArrayList<CoordMaker>(); 
	        					
	        					for (i=0; i< colist.size(); i++) 
	    	        			{
	    	        				// checks to see the pieces within current board 
	    	        				
	    	        				if(colist.get(i).getBase() == board.get(plank).GetWoodtype() ) 
	    	        				{
	    	        					firsttime = false;
	    	        				
	    	        				}
	    	        			}
	        					
	        					if(firsttime == true ) 
	        					{ //if there has been no pieces placed make the first one 0,0
	        						x= 0;
	        						y=0;
	        						canplace = true;
	        						CoordMaker cut = new CoordMaker(pieces.get(count).GetWoodtype(), ((ArrayList<Wood>) board).get(plank).GetWoodtype(), x, y, (int) pieces.get(count).GetHeight(),(int) pieces.get(count).GetWidth() );
        							colist.add(cut);
        							placement.add(cut);
        							
	        					}
	        					else { //after the first piece we now move to the actual algorithm
	        						
	        						for (int a=0; a < colist.size(); a++) {
	        							if(colist.get(a).getBase() == board.get(plank).GetWoodtype() ) {
	        								placement.add(colist.get(a));
	        							}
	        						}
	        					
	        					for (int q=0; q < placement.size(); q ++) { // checks if the  unplaced piece is colliding with any already placed piece
	        						
	        						for(int h =0; h < board.get(plank).GetHeight(); h++) {
	        							
	        							if(x < placement.get(q).getXsize() + placement.get(q).getX() && y <= placement.get(q).getY()+ placement.get(q).getYsize()) {
	        								x= placement.get(q).getXsize() + placement.get(q).getX(); // if collisions move to the end of the placed piece
	        								canplace = false;
	        								h = h-1;
	        								System.out.println(colist.get(temp).getX() + " times here: " + temp);
	        								if (pieces.get(count).GetHeight() + x > board.get(plank).GetHeight()) {
	        								h = h+1;
	        								y=h;
	        								x=0;
	        								
	        							}
	        								
	        							}
	        							else if(x + pieces.get(count).GetHeight()<= board.get(plank).GetHeight()) {// no collision = place here
	        								
	        								canplace = true;
	        								
	        								temp ++;
	        								break;
	        							}
	        						}
	        						if (canplace == true){
	        							CoordMaker cut = new CoordMaker(pieces.get(count).GetWoodtype(), ((ArrayList<Wood>) board).get(plank).GetWoodtype(), x, y, (int) pieces.get(count).GetHeight(),(int) pieces.get(count).GetWidth() );
	        							colist.add(cut);
	        							placement.add(colist.get(count));
	        							break;
	        						}
	        					}
	        					
	        					
	        					
	        				}
	        				
	        			}
	        				
	        			
	        		if (canplace = true) 
	        		 {			
	        			break;
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
	        } while(count < pieces.size());
	      
	       
	      for(int p =0; p< colist.size(); p++) {
	    	  System.out.println(colist.get(p).getBase() + ", " + colist.get(p).getX() + ", " + colist.get(p).getY());
	    	  
	      }
	            
	        return colist;
	        
	        
}
	        
	       static public ArrayList<Wood> results() {
	        	//will first create a rectangle from board and draw it
	    	   
	    	   return board;
	       }
	      
	    	   
	    	   
}




