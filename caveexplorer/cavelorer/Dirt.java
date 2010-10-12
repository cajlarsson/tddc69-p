package caveexplorer.cavelorer;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;


public class Dirt implements Tickable
{
   private boolean dug = false;
   private boolean indestructable = false;
   private int timeToLive = -1;
   private int dugBy = -1;
	
   private ArrayList<Physical> physicals;
	
   public Dirt()
   {
      physicals = new ArrayList<Physical>();
   }

   public void preformTick()
   {
      if(dug)
      {
	 timeToLive--;
      }
      if ((timeToLive < 0 )
	  &&!indestructable)
      {
	 dug = false;
	 //FIXME Kollapsa;
      }

      //FIXME kontrollera 
   }
	
   public void dig(int digger)
   {
      dugBy = digger;
      timeToLive = 2000; // FIXME MAGIC CONSTANTS
      dug = true;
   }

   public boolean isDug()
   {
      return dug;
   }
	
   public void digIndestrucable(int digger)
   {
      dugBy = digger;
      timeToLive = 2000; // FIXME MAGIC CONSTANTS
      dug = true;
      indestructable = true;
   }	

   public void addPhysical(Physical physical)
   {
      physicals.add(physical);
   }

   public int getPhysicalsSize()
   {
      return physicals.size();
   }
    
   public Physical getPhysical(int physical)
   {
      return physicals.get(physical);
   }
   
   public void removePhysical(Physical physical)
   {
      for (int i = 0; i < physicals.size(); i++)
      {
	 if (physicals.get(i) == physical)
	 {
	    physicals.remove(i);
	    break;
	 }
      }
   }

   public ArrayList<Physical> deadPhysicals()
   {
      ArrayList<Physical> out = new ArrayList<Physical>();
		
      for (Physical P : physicals)
      {
	 if (P.lives())
	 {
	    out.add(P);
	 }
	 else 
	 {
	    removePhysical(P);
	 }
      }
      return out;
   }

   public boolean hasBig()
   {
      for (Physical P : physicals)
      {
	 if (P.big())
	 {
	    return true;
	 }
      }
      return false;
   }

	
   public boolean empty()
   {
      return physicals.isEmpty();
   }

}

