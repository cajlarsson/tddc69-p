package cavelorer;

public class GameAction
{
	private Position position;
	private Direction direction;
	private ActionClass actionClass;
	private Physical physical;
	public GameAction(Position position,
			  ActionClass actionClass, 
			  Direction direction,
			  Physical physical)
	{
		this.position = position;
		this.direction = direction;
		this.actionClass = actionClass;
		this.physical = physical;
	}
	
       	public GameAction(Position position, ActionClass actionClass)
	{
	 	this(position, actionClass, Direction.UP, null);		
	}

	public GameAction(Position position,
			  ActionClass actionClass,
			  Physical physical)
	{
	 	this(position, actionClass, Direction.UP, physical);		
	}

	public ActionClass actionClass()
	{
		return actionClass;
	}

	public Direction direction()
	{
		return direction;
	}
	
	public Position position()
	{
		return position;
	}

	public Physical physical()
	{
		return physical;
	}
}