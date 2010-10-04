package caveexplorer.cavelorer;

import java.util.*;

public abstract class Weapon 
{
	private boolean canDig;
	private int range;
	private int cooldown;
	public Weapon(boolean canDig, int range)
	{
		this.canDig = canDig;
		this.range = range;
	}

	public boolean canDig()
	{
		return canDig;
	}
 
	public int getRange()
	{
		return range;
	}
	
	public void tick()
	{
		if (cooldown > 1)
		{
			cooldown--;
		}

	}
	
	public boolean ready()
	{
		return cooldown <= 0;
	}

	public abstract ArrayList<GameEvent> attackActions();
	public abstract ArrayList<GameEvent> digActions();

}