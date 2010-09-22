package cavelorer;

import java.util.*;

public class Contestant
{
	private ArrayList<Unit> units;
	private int base; //FIXME;
	private ArrayList<Position> knownSquares; //FIXME;
//	private ArrayList<Integer> enemas;
	private Monies money;
	
	private int mapWidth;
	private int mapHeight;
	
	private GameEngine game;

	public Contestant(GameEngine game)
	{
		this.game = game;
		money = new Monies();
		game.addTimeDependent(money);
		
		units = new ArrayList<Unit>();
		//FIXMEH
		//FIXME BASEUNTERFUNC
		
	}
	
	public void buildBase(Position xy)
	{
		//RETURN JOBBA HÄR
	}
	
	

	
	
}