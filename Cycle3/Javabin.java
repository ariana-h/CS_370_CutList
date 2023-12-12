import java.util.ArrayList;

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
	        int d=0;
	        
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
	        
	       
	         // runs sorting
	        
	        do { // will run through all pieces NOTE SAME LOGIC ERROR AS THE MULTI BOARD I THINK, SOMETHING TO DO WITH IT GOING TO BOTH BOARDS
	        	
	        	do { //will run through each stock sheet
	        		int traker=0;
	        		System.out.println(traker);
	        		boardArea = (int)board.get(plank).GetHeight() * (int)board.get(plank).GetWidth();
	        		for (CoordMaker col : colist) {
	        			if (col.getBase().equals(board.get(plank).GetName()) ) {
	        				traker++;
	        			}
	        		}
	        		firsttime= true;
	        		if(traker > 0) 
	        		{
	        			for (i=0; i< colist.size(); i++) 
	        			{
	        				// checks to see the pieces within current board 
	        				
	        				if(colist.get(i).getBase() == board.get(plank).GetName() ) 
	        				{
	        					pieceArea = (int)colist.get(i).getXsize() * (int)colist.get(i).getYsize();
	        					totArea= totArea + pieceArea;
	        					
	        				}
	        			}
	        			firsttime = false;
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
        							
        							System.out.println("first piece in " + board.get(plank).GetName() +cut.getPiece() );
        							
	        					}
	        					if (firsttime ==false) { //after the first piece we now move to the actual algorithm
	        						
	        						for (int a=0; a < colist.size(); a++) {
	        							if(colist.get(a).getBase() == board.get(plank).GetName() ) {
	        								placement.add(colist.get(a));
	        								
	        							}
	        						}
	        					
	        					 // checks if the  unplaced piece is colliding with any already placed piece
	        						do {
	        							System.out.println("current piece: " + pieces.get(count).GetName());
	        							
	        							for(CoordMaker lol : placement) {
	        								int fullx= (int) (lol.getXsize() + lol.getX() + InnerPanel.kerfThickness);
	        								int backx =  (int) (lol.getX() - InnerPanel.kerfThickness);
	        								int fully = (int) (lol.getY()+ lol.getYsize() + InnerPanel.kerfThickness);
	        								int backy = (int) (lol.getY()- InnerPanel.kerfThickness);
	        								System.out.println("This will show what piece its comparing: " +lol.getPiece());
	        								if(x < fullx && x+ pieces.get(count).GetWidth() >= backx && y < fully &&  y +pieces.get(count).GetHeight() >= backy ) { //NOTE : ADD THE OVERHANG CHECK
	        									canplace = false;
	        								}
	        								else {
	        									canplace = true;
	        								}
	        							}
	        						
	        						
	        						
	        							
	        							if(canplace == false) {
	        								
	        								x= (int) (placement.get(q).getXsize() + placement.get(q).getX() + InnerPanel.kerfThickness); // if collisions move to the end of the placed piece
	        								
	        								
	        								q++;
	        								
	        								temp++; //NOTE THIS IS THE PROBLEM IT'S NEEDED TO CONTROL THE AMOUNT OF PIECES BUT ALSO STOPS THE PIECES BEFORE A DROP CAN OCCUR!!!!!
	        								
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
        								if(temp >= placement.size()) {
        									notpos = true;
        									System.out.println("fuck I don't know");
        								}
	        							
	        							
        								
	        							}while (canplace == false && notpos == false);
	        						q=0;
	        						if(notpos == true && y+ pieces.get(count).GetHeight() > board.get(plank).GetHeight() ) {
	        						System.out.println("notpos is true this should come up");
	        						}
	        						if (canplace == true){
	        							CoordMaker cut = new CoordMaker(pieces.get(count).GetName(),  board.get(plank).GetName(), x, y, (int) pieces.get(count).GetWidth(),(int) pieces.get(count).GetHeight() );
	        							colist.add(cut);
	        							System.out.println("Placesize " +placement.size());
	        							
	        						}
	        					
	        					
	        					
	        					
	        				}
	        				
	        			}
	        				
	        			
	        		
	        		if(isSpace == false || notpos == true) {
	        			plank++;
	        		}
	        		
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
	      
	       
	      
	        return colist;
	       
	        
	        
}
	    	   
	    	   
}
