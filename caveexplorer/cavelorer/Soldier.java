package caveexplorer.cavelorer;

import java.util.*;
import caveexplorer.client.*;

public class Soldier extends Physical
{
    private ArrayList<GameOrder> orders;
    private ArrayList<GameAction> actions;
    private Direction direction;
    private Position position;
    private Weapon weapon; // FIXME fel typ, implementera
	
    private Contestant contestant;
	
    public Soldier(Contestant contestant)
    {
	super(true, false, 100,20);
	this.contestant = contestant;
	orders = new ArrayList<GameOrder>();
	actions = new ArrayList<GameAction>();
    }
	
    private void generateActions()
    {
	actions = null;
	actions = new ArrayList<GameAction>();
	actions.add( new GameAction(position,
				    ActionClasses.WAIT,
				    direction,this));
    }
	
    public GameAction nextAction()
    {
	if (contestant.getEnemies().isEmpty()) // If  enemies are seen
	    {
		switch (orders.get(0).getType())
		    {
		    case DIGTO:
			//do nothing or expand to complex
			break;
		    case MOVETO:
				
			break;
		    case SHOOTPOSITION:
			// do nothing here
			break;
		    case BUYWEAPON:
			break;
		    case EXPLODE:
			// should exist?
			break;
		    case WAIT:
			// nothing should be done
			break;
		    case GUARD:
			GameAction shoot = shootClosest();
			if (shoot != null) // kanske kan skjuta direkt
			    {
				return shoot; 
			    }else
			    {
				return new GameAction(position,
						      ActionClasses.WAIT,
						      direction,this);
			    }
				
				
			// check line and shoot if possible
		    }
			    
			
		//TODO anfall beroende på order
	    }
		
	if (actions.isEmpty())
	    {
		generateActions();
	    }
		
	GameAction temp = actions.get(0);
	actions.remove(0);
	return temp;		
    }

    public void onDeath()
    {
	
		
    }
	
		
    private Direction getLongDirection(Position position)
    {
	Position diff = this.position.subtract(position);
		
	if (Math.abs(diff.x) >- Math.abs(diff.y))
	    {
		if (diff.x <0)
		    {
			return Direction.LEFT;
		    }
		else
		    {
			return Direction.RIGHT;
		    }
	    }else
	    {
		if (diff.y <0)
		    {
			return Direction.DOWN;
		    }else
		    {
			return Direction.UP;
		    }
	    }
    }
	
    private GameAction shootClosest()
    {
	Position nearestPosition = null;
	double shortestDistance = 100000; // FIXME: fugly
		
	for (Position P : contestant.getEnemies())
	    {
		if (contestant.gotStraightLine(P, position)
		    && P.distance(position) < weapon.getRange()
		    && P.distance(position) < shortestDistance)
		    {
			shortestDistance = P.distance(position);
			nearestPosition = P;
		    }
	    }

	if (nearestPosition != null
	    && weapon.ready())

	    {
			
		return new GameAction(nearestPosition,
				      ActionClasses.SHOOT,
				      getLongDirection(nearestPosition),
				      this);
	    }
		
	return null;
    }
		
    public  void preformTick()
    {
	super.preformTick();
	weapon.tick();
    }
    
    public Units getType()
    {
	return Units.MY_SOLDIER;
    }
}