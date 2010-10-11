package caveexplorer.cavelorer;
import java.util.*;
import caveexplorer.client.*;
    

public abstract class Physical implements Tickable
{
    private boolean big;
    private boolean blind;
    private boolean alive = true;
    
    private int tickToHeal;
    private int ticks = 0;
	
    private int health;
	
    private int maxHealth;
    
    private int owner;
    private int ID;
    

    public Physical(boolean big,boolean blind, int maxHealth, int tickToHeal,
		    int Owner,int ID)
    {
	this.tickToHeal = tickToHeal;
	this.health = maxHealth;
	this.maxHealth = maxHealth;
	this.big = big;
	this.blind = blind;
	this.owner = owner;
	this.ID = ID;
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
    
    public void preformTick()

    {
	healthTick();
	nextAction();
    }
	
    public void changeHealth(int healthDelta)
    {
	health += healthDelta;
	if ( health < 0)
	    {
		alive = false;
		maxHealth = -1;
	    }
    }
    public int owner()
    {
	return owner;
    }
    
    public int ID()
    {
	return ID;
    }
	
    public abstract GameAction nextAction(); // ska ge nästa action objekt

    public abstract void onDeath();
	 
    public abstract Units getType();
}