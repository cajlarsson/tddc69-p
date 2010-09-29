package cavelorer;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.event.*;

public class GameEngine implements ActionListener
{
	private int mapWidth = 80;  //FIXME MAGIC CONTESTANT
	private int mapHeight = 70; //FIXME MAGIC CONTESTANT
	
	private ArrayList<Contestant> contestants;
	
	private ArrayList<Tickable> timeDependents;

	private ArrayList<Physical> deadPhysicals;
	
	private Dirt[][] map; // FIXME //HAS WORKS!
	
	private int timesTicked = 0;
	
	private void initMap(int width, int height)
	{
		map = new Dirt[width][height];

		for (int x = 0; x < width; x++)
		{
			for (int y = 0; y <height; y++)
			{
				map[x][y] = new Dirt();
				addTimeDependent(map[x][y]);
			}
		}
	}
	
	public GameEngine()
	{
		Timer tickTimer = new Timer(100, this); //FIXME MAGIC CONSTANTS
		initMap(mapWidth,mapHeight); //FIXME MAGIC CONSTANTS
		
		timeDependents = new ArrayList<Tickable>();
		contestants = new ArrayList<Contestant>();
				
		Contestant contestant1 = new Contestant(this,0);
		Contestant contestant2 = new Contestant(this,1);
		
		contestant1.buildBase(new Position(0,mapHeight/2 -3));
		contestant2.buildBase(new Position(mapWidth -6 ,mapHeight/2 -3));
		
		contestants.add(contestant1);
		contestants.add(contestant2);
		
		
		//FIXME insert stuff here
		
		tickTimer.start();       //last!?.
		
		while(true)
		{}		
	}
	
	public void actionPerformed(ActionEvent e)
	{
		doTick();
	}
	
	public void doTick()
	{
		timesTicked++;
		deadPhysicals = new ArrayList<Physical>();
		for (int x = 0; x < mapWidth; x++)
		{
			for (int y = 0 ; y <mapHeight; y++)
			{
				deadPhysicals.addAll(
					map[x][y].deadPhysicals());
			}
		}
		
		for (Tickable i : timeDependents)
		{
			i.preformTick();
		}
		//FIXME uppdatera enheter osv.
	}	

	public void addTimeDependent(Tickable dependee)
	{
		timeDependents.add(dependee);
	}

	public void dig(Position position, int ID)
	{
		map[position.x][position.y].dig(ID);		
	}
	
	public void digIndestrucable(Position position, int ID)
	{
		map[position.x][position.y].digIndestrucable(ID);		
	}

	public boolean isDug(Position position)
	{
		return map[position.x][position.y].isDug();
	}
	
	public void removePhysical(Position position, Physical physical)
	{
		map[position.x][position.y].removePhysical(physical);
	}
	
	public void addPhysical(Position position, Physical physical)
	{
		map[position.x][position.y].addPhysical(physical);
	}
	
	public Position mapSize()
	{
		return new Position(mapWidth,mapHeight);
	}

	public boolean gotBig(Position position)
	{
		return map[position.x][position.y].hasBig();
	}

	public boolean mapEmpty(Position position)
	{
		return map[position.x][position.y].empty();
	}
}