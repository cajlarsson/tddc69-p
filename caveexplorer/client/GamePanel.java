package caveexplorer.client;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import caveexplorer.cavelorer.*;

public class GamePanel extends JComponent
{
	private ArrayList<MapLayer> layers;
	private ArrayList<MovableUnit> movableUnits;
		
	private class ClickListener extends MouseAdapter 
	{
		public void mouseClicked(MouseEvent ev) 
		{
		
	        }
			
		public void mousePressed(MouseEvent ev)
		{
			//	dostuff(ev.getX() /10, ev.getY()/10) ;	
			addUnit(Units.HOLE,
				new Position( ev.getX() /10, ev.getY() /10));
		}
	}
	
	public GamePanel(int width, int height)
	{
		layers = new ArrayList<MapLayer>();
		movableUnits = new ArrayList<MovableUnit>();
		layers.add(generateBottomLayer(width,height)); //DIRT
		layers.add(new MapLayer()); //DIG
		layers.add(new MapLayer());//FLOOR
		layers.add(new MapLayer()); //UNIT
		layers.add(new MapLayer());//PROJECTILES		

		addMouseListener(new ClickListener());
	}
	
	public void paintComponent(Graphics g)
	{
		g.setColor(Color.red);
		g.fillRect(0,0,this.getWidth( ), this.getHeight());
		
		for (MapLayer l : layers)
		{
			l.paintLayer(g, this);
		}
	}
	
	private MapLayer generateBottomLayer(int width, int height)
	{
		MapLayer temp = new MapLayer();
		
		for ( int i = 0 ; i <width; i++)
		{
			for ( int j = 0 ; j < height; j++)
			{
				temp.add(new PaintImage(Images.DIRT,
							new Position(i,
								     j)));	 
			}
		}
		
		return temp;		
	}

	public void addUnit(Units unitType, Position position)
	{
		movableUnits.add( new MovableUnit(layers,
						  unitType,
						  position));
		repaint();
		
	}
       
	

}


