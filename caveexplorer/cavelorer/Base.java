package caveexplorer.cavelorer;
import caveexplorer.client.*;
public class Base extends Physical
{
    public Base(int owner,int ID)
    {
	super(true, false, 1000, 100,owner,ID);
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
