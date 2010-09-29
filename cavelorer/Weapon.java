package cavelorer;

import java.util.*;

public abstract class Weapon 
{
	private boolean canDig;
	private int range;
	
	public Weapon(boolean canDig, int range)
	{
		this.canDig = canDig;
		this.range = range;
	}

	public boolean canDig()
	{
		return canDig;
	}
 
	public int range()
	{
		return range;
	}
	
	public abstract ArrayList<GameEvent> shootActions();
	public abstract ArrayList<GameEvent> digActions();
}