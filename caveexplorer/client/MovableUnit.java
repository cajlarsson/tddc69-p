package caveexplorer.client;

import caveexplorer.cavelorer.*;
import java.util.*;

public  class MovableUnit
{
    private MapLayer layer;
    private PaintImage paintImage;
    private int ID;

    public MovableUnit(ArrayList<MapLayer> layers, 
		       Units unitType,
		       Position position,
		       int ID)
    {
	this.ID = ID;
	this.layer = layers.get(Util.getLayer(unitType).ordinal());
	this.paintImage = new PaintImage( Util.getImage(unitType), position);
	layer.add(paintImage);
    }
    
    public void removeImage()
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
	// subtrahera nya posen från gammla och dela med antalet tick du vill att
	// det ska ta att flytta och lägg till lika många ordrar till orderlistan.
	// FIXME IMPLEMENT?
	paintImage.setPosition(position);
    }
    
    public Position getPosition()
    {
	return paintImage.getPosition();
    }
    
    public int getID()
    {
	return ID;
    }

}

