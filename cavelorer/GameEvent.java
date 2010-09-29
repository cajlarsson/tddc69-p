package cavelorer;

public class GameEvent
{
	private EventClasses type;
	private Position position;
	private Direction direction;
	private int magnitude;

	public GameEvent(EventClasses type,
			 Position position,
			 Direction direction,
			 int magnitude)
	{
		this.direction = direction;
		this.position = position;
		this.direction = direction;			
		this.magnitude = magnitude;
	}
	
	public void applyOrigin(Position position)
	{
		this.position = this.position.add(position);
	}

	public void setDirection(Direction direction)
	{
		this.direction = direction;
	}
	
	public int getMagnitude()
	{
		return magnitude; 
	}
    
    
}