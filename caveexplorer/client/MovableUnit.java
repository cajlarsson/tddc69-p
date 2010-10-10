package caveexplorer.client;

import caveexplorer.cavelorer.*;
import java.util.*;

public  class MovableUnit
{
   private MapLayer layer;
   private PaintImage paintImage;
   private int ID;
   private int unitSpeed;
   private Arraylist<UnitMessage> messageQueue;
   // FIXME WHAT IZ THIS unitType ANYWAYS???!!!

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
      position tempPosition = paintImage.getPosition();
      position tempVector = tempPosition.subtract(position)/unitSpeed;
      for( int i = 0; i < unitSpeed; i++)
      {
	 tempPosition += tempVector;
	 messageQueue.add(new MoveUnitMessage(MessageType.MOVE,
					      ID, 
					      tempPosition,
					      unitType));
      }
      // subtrahera nya posen från gammla och dela med antalet tick du vill att
      // det ska ta att flytta och lägg till lika många ordrar till orderlistan.
      // FIXME HAZ WORKS?
      //paintImage.setPosition(position);
   }
   
   public int getID()
   {
      return ID;
   }
}

