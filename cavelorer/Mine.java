package cavelorer;
import java.util.*;
 
public class Mine extends Weapon
{
	public Mine()
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