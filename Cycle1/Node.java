package Cycle1;


public class node {


	   public boolean isroot = false;
	    public String name;
	    public double x;
	    public double y;
	    public double w;
	    public double h;
	    public boolean used = false;
	    public node right = null;
	    public node down = null;
	    public node fit = null;

	    public node(String name, double w, double h) {
	        this.name = name;
	        this.w = w;
	        this.h = h;
	    }

	    public node(double x, double y, double w, double h) {
	        this.x = x;
	        this.y = y;
	        this.w = w;
	        this.h = h;
	        if(x == 0 && y == 0){
	            this.isroot = true;//this is only necessary for me to print 'Pack Starts Here' in the example code
	        }
	    }
		/*int key;

		node left, right;
		public node(int item, int r, double W, double L)
		{
			key = item;
			left = right = null;
		}
*/
	}
