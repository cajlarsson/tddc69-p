package caveexplorer.cavelorer;

import java.util.*;

public final  class Explosion
{
	static	private ArrayList<Position> effectedPositions(int radius)
	{
		ArrayList<Position> temp = new ArrayList<Position>();
		
		for (int x = -radius; x < radius; x++)
		{
			for(int y = -radius; y <radius; y++)
			{
				if (Math.round(Math.sqrt((x * x) + (y * y))) 
				    < radius)
				{
					temp.add(new Position(x,y));
				}
			}
		}
		return temp;
	}
	
	static	public ArrayList<GameEvent> eventList( int radius)
	{
		ArrayList<GameEvent> temp = new ArrayList<GameEvent>();
		
		Random random = new Random();
		
		for ( Position p : effectedPositions(radius))
		{
			temp.add(new GameEvent(
					 EventClasses.EXPLOSION,
					 p,
					 Direction.NONE,
					 random.nextInt(70) + 30));
		}
		
		for ( Position p : effectedPositions(radius))
		{
			GameEvent digEvent = new GameEvent(
				EventClasses.DIG,
				p,
				Direction.NONE,
				0);
			
			GameEvent collapseEvent = new GameEvent(
				EventClasses.DIG,
				p,
				Direction.NONE,
				0);
			
			if (random.nextInt(10) > 6)
			{
				temp.add(digEvent);
			}else
			{
				temp.add(collapseEvent);
			}
		}
		return temp;
	}
}