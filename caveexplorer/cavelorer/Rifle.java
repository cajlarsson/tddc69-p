package caveexplorer.cavelorer;
import java.util.*;
public class Rifle extends Weapon
{
	public Rifle()
	{
		super(false,200);
	}
	
	public ArrayList<GameEvent> attackActions()
	{
		ArrayList<GameEvent> temp = new ArrayList<GameEvent>();
		temp.add(new GameEvent(EventClasses.SHOT,
				       new Position(0,0),
				       Direction.NONE,
				       30));
		return temp;
	}
	
	public ArrayList<GameEvent> digActions()
	{
		return null;
	}
}