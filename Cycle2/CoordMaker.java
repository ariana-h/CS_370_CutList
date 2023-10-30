
public class CoordMaker {
	private String piece, Base;
	private int x,y, xsize, ysize;
	public CoordMaker() {
	}
	
	public CoordMaker(String wood, String base, int xsize, int ysize) {
		this.piece=wood;
		this.Base =base;
		this.xsize = xsize;
		this.ysize = ysize;
	}
	
	public CoordMaker(String wood, String base, int x, int y, int xsize, int ysize) {
		this.piece=wood;
		this.Base = base;
		this.x=x;
		this.y=y;
		this.xsize = xsize;
		this.ysize = ysize;
	}
	

	public String getPiece() {
		return piece;
	}

	public void setPiece(String piece) {
		this.piece = piece;
	}

	public String getBase() {
		return Base;
	}

	public void setBase(String base) {
		Base = base;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getXsize() {
		return xsize;
	}

	public void setXsize(int xsize) {
		this.xsize = xsize;
	}

	public int getYsize() {
		return ysize;
	}

	public void setYsize(int ysize) {
		this.ysize = ysize;
	}
	

}
