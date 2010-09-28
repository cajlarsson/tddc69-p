package cavelorer;

public class Base extends Physical
{
	public Base()
	{
		super(true, false, 1000, 100);
	}
	
	public GameAction nextAction() //FIXME IMPLEMENT
	{
	    return new GameAction(null, null, null, null );

	}
	
	public void onDeath()
	{
		//FIXME verkligen snyggt?
	}
}