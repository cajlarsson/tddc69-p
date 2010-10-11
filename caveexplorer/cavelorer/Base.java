package caveexplorer.cavelorer;
import caveexplorer.client.*;
public class Base extends Physical
{
    public Base()
    {
	super(true, false, 1000, 100);
    }
	
    public GameAction nextAction()
    {
	return new GameAction(null, ActionClasses.WAIT);
    }

    public void onDeath()
    {
	//FIXME verkligen snyggt?
    }

    public Units getType()
    {
	return Units.MY_SOLDIER;
    }
}
