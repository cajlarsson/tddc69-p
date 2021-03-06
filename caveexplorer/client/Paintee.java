package caveexplorer.client;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import caveexplorer.cavelorer.*;

public abstract class Paintee
{
   private Position position;
   private int ownerID = -1;
   public Paintee(Position position)
   {
      this.position = position;
   }

   public abstract void Paint(Graphics g, JComponent owner);
	
   public Position getPosition()
   {
      return position;
   }
		
   public void setPosition(Position position)
   {
      this.position = position;
   }
    
   public int getOwnerID()
   {
      return ownerID;
   }

   public void setOwnerID(int ownerID)
   {
      this.ownerID = ownerID;
   }
}