package cavelorer;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.event.*;

public class GameEngine implements ActionListener
{
	private ArrayList<Contestant> contestants;
	
	private ArrayList<Tickable> timeDependents;
	
	private Dirt[][] map; // FIXME
	
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

		timeDependents = new ArrayList<Tickable>();
		
		contestants = new ArrayList<Contestant>();
		contestants.add(new Contestant());
		contestants.add(new Contestant());

		initMap(80,70); //FIXME MAGIC CONSTANTS
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


}