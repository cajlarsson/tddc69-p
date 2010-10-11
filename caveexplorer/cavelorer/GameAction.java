package caveexplorer.cavelorer;

public class GameAction
{
    private Position position;
    private Direction direction;
    private ActionClasses actionClass;
    private Physical physical;
    public GameAction(Position position,
		      ActionClasses actionClass, 
		      Direction direction,
		      Physical physical)
    {
	this.position = position;
	this.direction = direction;
	this.actionClass = actionClass;
	this.physical = physical;
    }
	
    public GameAction(Position position, ActionClasses actionClass)
    {
	this(position, actionClass, Direction.UP, null);		
    }

    public GameAction(Position position,
		      ActionClasses actionClass,
		      Physical physical)
    {
	this(position, actionClass, Direction.UP, physical);		
    }


    public ActionClasses actionClass()
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