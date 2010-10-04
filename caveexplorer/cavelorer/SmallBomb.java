package caveexplorer.cavelorer;
import java.util.*;
 
public class SmallBomb extends Weapon
{
	public SmallBomb()
	{
		super(false,0);
	}
	
	public ArrayList<GameEvent> attackActions()
	{
		return Explosion.eventList(2);
	}
			
	public ArrayList<GameEvent> digActions()
	{
		return null;
	}
}