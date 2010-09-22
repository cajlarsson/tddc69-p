package cavelorer
import java.util.*;
    

public abstract class Physical 
{
	private boolean big;
	private boolean blind;
	private boolean alive = true;

	private int tickToHeal;
	private int ticks = 0;
	
	private int health;
	
	private int maxHealth;
	
	public Physical(boolean big,boolean blind, int maxHealth, int tickToHeal)
	{
		this.tickToHeal = tickToHeal;
		this.health = maxHealth;
		this.maxHealth = maxHealth;
		this.big = big;
		this.blind = blind;
	}
	
	protected void healthTick()
	{
		ticks++;
		
		if (ticks > tickToHeal)
		{
			ticks = 0;
			health++;
		}
		
		if (health > maxHealth)
		{
			health = maxHealth;
		}
	}
	
	public boolean lives()
	{
		return alive;
	}
	
	public boolean big()
	{
		return big;
	}

	public boolean blind()
	{
		return blind;
	}

}