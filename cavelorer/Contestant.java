package cavelorer;

import java.util.*;

public class Contestant
{
	private ArrayList<Physical> physicals;
//	private int base; //FIXME;
	private ArrayList<Position> knownSquares; //FIXME;
	private ArrayList<Position> dugSquares;
	private ArrayList<Position> seenEnemies;
	private Monies money;
	
//	private int mapWidth;
//	private int mapHeight;
	private int ID;
	
	private GameEngine game;

	public Contestant(GameEngine game, int ID)
	{
		this.game = game;
		this.ID = ID;
		money = new Monies();
		game.addTimeDependent(money);
		
		dugSquares = new ArrayList<Position>();

		physicals = new ArrayList<Physical>();
		
		//mapWidth = game.mapSize().x;
		//mapHeight = game.mapSize().y;
		
		//FIXMEH ??
	}
	
	public void buildBase(Position position) // tar övre vänstra hörnet
	{
//FIXME KLAR?
		for (int x = position.x; x < (position.x +6) ; x++)
		{
			for (int y = position.y; y < (position.y +6) ; y++)
			{
				game.digIndestrucable( new Position(x,y), ID);
			}
		}
		
		Base base = new Base();
		
		game.addPhysical(position, base);
		game.addPhysical(new Position(position.x + 1, position.y)
				 , base);
		game.addPhysical(new Position(position.x, position.y + 1)
				 , base);
		game.addPhysical(new Position(position.x + 1, position.y + 1)
				 , base);
		
		physicals.add(base);
		game.addTimeDependent(base);
	}
	
	public void  killPhysicals(ArrayList<Physical> deadPhysicals)
	{
		for (Physical P: deadPhysicals)
		{
			for (int i = 0; i < physicals.size(); i++)
			{
				if ( P == physicals.get(i))
				{
					physicals.get(i).onDeath(); 
					//FIXME inte färdigt egentligen
					physicals.remove(i);
				}
			}
		}
	}
	
	public void applyAction(GameAction action)
	{
		switch (action.actionClass()) 
		{
		case DIG: 
			Position position = action.position().step(
					 action.direction());
			game.dig(position, ID);
			dugSquares.add(position);
			break;

		case SHOOT: 
			//TODO 
			break;

		case EXPLODE:  
			//TODO
			break;

		case MOVE:
			if (game.isDug(action.position())
			    && game.gotBig(action.position()))
			{
				game.removePhysical(
					action.position(),
					action.physical());
				game.addPhysical(
					action.position().step(action.direction()),
					action.physical());
			}
			break;

		case SPAWN: 
			if (game.isDug(action.position()))
			{
				physicals.add(action.physical());
				game.addPhysical( action.position(),
						  action.physical());
			}
			break;

		case PICKUP:  
			if (!game.mapEmpty(action.position().step(
						action.direction()))
			    && !game.gotBig(action.position().step(
						action.direction()))
			    && game.isDug(action.position().step(
						action.direction())))
			{
				
//TODO fyll ut
			       
				
				
			}
			break;
		case WAIT:
		    break;
			
		}
	}
	
	public ArrayList<Position> getEnemies()
	{

		return seenEnemies;
	}
}