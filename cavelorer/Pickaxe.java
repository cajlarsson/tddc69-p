package cavelorer;
import java.util.*; 
public class Pickaxe extends Weapon
{
	public Pickaxe()
	{
		super(true,1);
	}
	
	public ArrayList<GameEvent> attackActions()
	{
		ArrayList<GameEvent> temp = new ArrayList<GameEvent>();
		temp.add(new GameEvent(EventClasses.MELEE,
				       new Position(0,0),
				       Direction.NONE,
				       100));
		return temp;
	}
			
	public ArrayList<GameEvent> digActions()
	{
		ArrayList<GameEvent> temp = new ArrayList<GameEvent>();
		temp.add(new GameEvent(EventClasses.DIG,
				       new Position(0,0),
				       Direction.NONE,
				       100));
		return temp;

	}
}