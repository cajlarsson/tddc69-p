package caveexplorer.client;

import caveexplorer.cavelorer.*;
import java.util.*;

public  class MovableUnit
{
	private MapLayer layer;
	private PaintImage paintImage;
	
	public MovableUnit(ArrayList<MapLayer> layers, 
			   Units unitType,
			   Position position)
	{
		this.layer = layers.get(Util.getLayer(unitType).ordinal());
		this.paintImage = new PaintImage( Util.getImage(unitType),
						  position);
		layer.add(paintImage);
	}
	
	public void RemoveImage()
	{
		for( int i = 0; i < layer.size(); i++)
		{
			if (layer.get(i) == paintImage)
			{
			    layer.remove(i);
			    break;
			}
		}
	} 
	
	public void moveTo(Position position)
	{
		paintImage.setPosition(position);
	}
}

