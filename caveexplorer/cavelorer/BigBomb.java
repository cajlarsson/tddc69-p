package cavelorer;
import java.util.*;
 
public class BigBomb extends Weapon
{
	public BigBomb()
	{
		super(false,0);
	}
	
	public ArrayList<GameEvent> attackActions()
	{
		return Explosion.eventList(5);
	}
			
	public ArrayList<GameEvent> digActions()
	{
		return null;
	}
}