package cavelorer;
import java.util.*;
public class Shotgun extends Weapon
{
	public Shotgun()
	{
		super(false,20);
	}
	
	public ArrayList<GameEvent> attackActions()
	{
		ArrayList<GameEvent> temp = new ArrayList<GameEvent>();
		
		GameEvent shot = new GameEvent(EventClasses.SHOT,
				       new Position(0,0),
				       Direction.NONE,
				       5);
		
		for (int i = 0 ; i <30; i++)
		    {
			temp.add(shot);
		    }
		
		return temp;
	}
	
	public ArrayList<GameEvent> digActions()
	{
		return null;
	}
}