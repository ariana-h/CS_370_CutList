package Cycle1;



import java.util.ArrayList;
import java.util.ListIterator;



public class Packer {
	

	
		private final ArrayList <node> root = new ArrayList();
		private int boxcount;
		
		public Packer (int boxcount, double L, double W) {
			this.boxcount = boxcount;
			
			for(int i = 0; i< boxcount; i++) {
				this.root.add(new node(0,0,L,W));}
			}
			
			public void fit (ArrayList<node> blocks) {
				node nod;
				node block;
				ListIterator<node> blockitr = (ListIterator<node>) blocks.listIterator();
		        
			
				 int n=0;
			        while (n<this.boxcount && blockitr.hasNext()) {
			            block = blockitr.next();            
			            if ((nod = this.findNode(this.root.get(n), block.w, block.h))!=null) {
			                block.fit = this.splitNode(nod, block.w, block.h);
			                if(nod.isroot){
			                    block.fit.isroot = true;
			                }
			            }else{
			                n++; 
			                if(blockitr.hasPrevious())
			                    blockitr.previous();
			            }
			        }
			    }

			    public node findNode(node root, double w, double h) {
			        if (root.used) {
			            node right = findNode(root.right, w, h);
			            return (right != null ? right : findNode(root.down, w, h));
			        } else if ((w <= root.w) && (h <= root.h)) {
			            return root;
			        } else {
			            return null;
			        }
			    }

			    public node splitNode(node node, double w, double h) {
			        node.used = true;
			        node.down = new node(node.x, node.y + h, node.w, node.h - h);
			        node.right = new node(node.x + w, node.y, node.w - w, h);
			        return node;
		

	
			    }
}