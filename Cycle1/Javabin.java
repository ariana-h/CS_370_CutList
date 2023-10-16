package Cycle1;
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
            
	       static  ArrayList<node> blocks = new ArrayList();
	       static  ArrayList<Wood> pieces = new ArrayList<Wood>();
	       static  ArrayList<Wood> board = new ArrayList<Wood>(); 
	       static ArrayList<Wood> inhere;
	        
	        static int i, c; 
	        static double cw, cl, bw, bl;
	        
	        
	 public static void pop (ArrayList<Wood> List) {
		  inhere = List; //populates a list for the algorithm
	        }
	
	        
	 public static void work () {    // separates the list into the "cuts" and board   
	        int d=0, val = 0;
	        
	        for (i = 0; i< inhere.size(); i++) {
	        	
	        	

	        	if(inhere.get(i).GetWoodtype().contains("Board"))
	        			{
	        		System.out.println("board size: " + board.size()); // test print
	        		System.out.println("in board"); // test print
		        	bl= inhere.get(i).GetHeight();
		        	bw= inhere.get(i).GetWidth();
		        	Wood run = new Wood(inhere.get(i).GetWoodtype(), inhere.get(i).GetGrain(), bl,  bw, 1);
	        		board.add(run); //populates the board list
	        		System.out.println(board.get(d).GetWoodtype() + " " +board.get(d).GetHeight() + " " + board.get(d).GetWidth()); //test print
	        		Wood held = new Wood();
	        		inhere.remove(i);// takes the boards out of inhere
	        		System.out.println(inhere.get(i).GetWoodtype());
	        		d++; //test value
	        	}
	        	else {
	        		
	        		System.out.println("in piece");
		        	cl = inhere.get(i).GetHeight();
		        	cw = inhere.get(i).GetWidth();
		        	String carrot = "figure " + val;
	        		blocks.add(new node(carrot, cl, cw)); // gets the length and width of inhere and puts it into a blocks node
	        		// would only work like that IDK why
	        		
	        		System.out.println(blocks.get(val).w + " " + blocks.get(val).h); // test print
	        		val++; // test value
	        	}
	        }
	        }
	        
	        public  static ArrayList<Wood> alg (ArrayList<Wood> List) {	
	        	
	        pop(List); // runs pop
	        
	        work(); // runs work

	        Collections.sort(blocks, new Comparator<node>() { // compares blocks with a new node based on length 
	            @Override
	            public int compare(node a, node b) {
	            	
	                return (Double.compare(b.w, a.w)); //doing the sort based on the length, you can change it accordingly to your needs.
	            }
	        });
	        c=0;
	        i=0;
	        Packer packer = new Packer(1, board.get(0).GetHeight(), board.get(0).GetWidth()); // sets up the packer with the first board only for testing purposes
	       
	    	   
	    	   System.out.println(" starting pak");// test print
	    
	        packer.fit(blocks); //runs packer and sorts pieces on the board
	        
	      
	       
	      
	            for (i=0;i < inhere.size(); i++) { //creates the ratio and puts it into the pieces list 
		        	cl = inhere.get(i).GetHeight();
		        	cw = inhere.get(i).GetWidth();
		        	bl = board.get(0).GetHeight();
		        	bw = board.get(0).GetWidth();
	                    cl=cl/bl;
	                    cl=cl/100;
	                    cw=cw/bw;
	                    cw =cw / 100;
	        	        Wood res = new Wood (inhere.get(i).GetWoodtype(), inhere.get(i).GetGrain(), cl,  cw, 1);
	                    pieces.add(res);
	                    System.out.println("result:" );
	                    System.out.println(pieces.get(i).GetWoodtype() + " " +pieces.get(i).GetHeight() + " " + pieces.get(i).GetWidth()); // test print
	                 
	            
	        }	        for (i=0; i<= pieces.size(); i++) {
	        
	        }
	        return pieces;
	        //runs results

	        
}
	        
	       static public double results() {
	        	//will first create a rectangle from board and draw it
	    	   
	    	   return board.get(0).GetWidth();
	    	   }

	    	   
	    	   
	    	   //will then take the length and width of pieces and coordinates from packer and draw on top of the "board"
	        	
	        }
