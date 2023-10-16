package ArchSpike;
public class Wood {

	private double Length,Width;
	private int Amount;
	private String Wood , Grain , Name;
	
	public Wood()
	{		
	}
	
	public Wood(String wood,String G,double L,double W,double A) {
		this.Wood = wood;
		this.Grain = G;
		this.Length = L;
		this.Width = W;
		this.Amount = (int)Math.round(A);
	}
	
	public Wood(String wood,String G,double L,double W,double A , String N) {
		this.Wood = wood;
		this.Grain =G;
		this.Length = L;
		this.Width = W;
		this.Amount = (int)Math.round(A);
		this.Name = N;
	}
	
	public void RotateWood(Wood W)
	{
		double L = Width;
		this.Width = Length;
		this.Length = L;
	}

	public double GetLength() {
		return Length;
	}
	
	public double GetWidth() {
		return Width;
	}
	
	public double GetAmount() {
		return Amount;
	}
	
	public String GetName() {
		if(Name != null)
		return Name;
		else
		return "N/A";
	}

	public String GetWoodtype() {
		return Wood;
	}

	public String GetGrain() {
		return Grain;
	}
}
