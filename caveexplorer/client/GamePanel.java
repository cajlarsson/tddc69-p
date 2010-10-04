package caveexplorer.client;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import caveexplorer.cavelorer.*;
public class GamePanel extends JComponent
{
	private ArrayList<MapLayer> layers;
	
       	public GamePanel(int width, int height)
	{
		layers = new ArrayList<MapLayer>();
		layers.add(generateBottomLayer(width,height));
	}
	
	public void paintComponent(Graphics g)
	{
		g.setColor(Color.red);
		g.fillRect(0,0,this.getWidth( ), this.getHeight());
		
		for (MapLayer l : layers)
		{
			l.PaintLayer(g);
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
							new Position(i,j)));	 
			}
		}

		return temp;		
	}

	
}


