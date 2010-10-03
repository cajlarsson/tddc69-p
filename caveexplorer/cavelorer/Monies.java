package cavelorer;

public class Monies implements Tickable
{
	private	int amount = 0;
//	private int divisor = 1;
	
	public void preformTick()
	{
		amount++;
	}
	
	public void change(int amount)
	{
		this.amount += amount;
	}
	
	public int get()
	{
		return amount;
	}
}
