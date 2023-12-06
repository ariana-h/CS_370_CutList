
public class Wood {

	private double Height,Width;
	private int Amount;
	private String Wood , Grain , Name;
	
	public Wood()
	{		
	}
	
	public Wood(String wood,String G,double L,double W,double A) {
		this.Wood = wood;
		this.Grain = G;
		this.Height = L;
		this.Width = W;
		this.Amount = (int)Math.round(A);
	}
	
	public Wood(String wood,String G,double L,double W,double A , String N) {
		this.Wood = wood;
		this.Grain =G;
		this.Height = L;
		this.Width = W;
		this.Amount = (int)Math.round(A);
		this.Name = N;
	}
	
	public void RotateWood(Wood W)
	{
		double L = Width;
		this.Width = Height;
		this.Height = L;
	}

	public double GetHeight() {
		return Height;
	}
	
	public void SetHeight(double d) {
		this.Height = d;
	}
	
	public double GetWidth() {
		return Width;
	}
	
	public void SetWidth(double d) {
		this.Width = d;
	}
	
	public int GetAmount() {
		return Amount;
	}
	public void SetAmount(int i)
	{
		this.Amount = i;
	}
	
	public String GetName() {
		if(Name != null)
		return Name;
		else
		return null;
	}

	public String GetWoodtype() {
		return Wood;
	}

	public String GetGrain() {
		return Grain;
	}
	
	public void GetWood()
	{
		System.out.println("Wood Type: " + Wood);
		System.out.println("Wood Grain: " + Grain);
		System.out.println("Wood Length: " + Height);
		System.out.println("Wood Width: " + Width);
		System.out.println("Wood Amount: " + Amount);
		System.out.println("Wood Name: " + Name);
	}
}