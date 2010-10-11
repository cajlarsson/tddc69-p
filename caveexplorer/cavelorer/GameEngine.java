package caveexplorer.cavelorer;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.event.*;
import caveexplorer.client.*;
import java.io.*;

public class GameEngine  implements ActionListener
{
    private int mapWidth;
    private int mapHeight;
	
    private ArrayList<Contestant> contestantArray;
    private ArrayList<Tickable> timeDependents;
    private ArrayList<Physical> deadPhysicals;
	
    private Dirt[][] map; 
	
    private int timesTicked = 0; //OVERFLOW?
    
    private int nextID = 0;
	
    private Timer tickTimer;
    
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
	
    public GameEngine(int width, int height)
    {
	mapHeight = height;
	mapWidth = width;
		
	timeDependents = new ArrayList<Tickable>();

	tickTimer = new Timer(100, this); 
	initMap(mapWidth,mapHeight); 
		

	contestantArray = new ArrayList<Contestant>();
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
	
	for (Contestant c : contestantArray)
	    {
		c.processMessages();	
	    }
	
	//FIXME uppdatera enheter osv.
    }	

    /******************************************************************************/
    //lol


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

    public void start()
    {
	tickTimer.start();   

    }
    
    public void addContestant(Position basePosition,
			      MessageOutput msgOutput)
    {
	Contestant contestant = new Contestant( this, nextID++);
	contestant.buildBase(basePosition);
	msgOutput.setMessageOutput(contestant);
	contestantArray.add(contestant);
    }

    public void setMessageOutput(MessageOutput msgOutput)
    {
	
    }

}