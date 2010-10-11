package caveexplorer.cavelorer;

import java.util.*;
import caveexplorer.client.*;
import java.io.*;

public class Contestant implements MessageOutput
{
   private ArrayList<Physical> physicals;
   //	private int base; //FIXME;
   private ArrayList<Position> knownSquares; //FIXME;
   private ArrayList<Position> dugSquares;
   private ArrayList<Position> seenEnemies;
   private ArrayList<CaveMessage> msgQueue;

   private Monies money;
   private ObjectInterface msgInterface;
    
   private MessageOutput msgOutput;
   
   private int ID;
      
   private GameEngine game;

   public Contestant(GameEngine game, int ID)
   {
      this.game = game;
      this.ID = ID;
      this.msgInterface = msgInterface;
            
      money = new Monies();
      game.addTimeDependent(money);
		
      dugSquares = new ArrayList<Position>();

      physicals = new ArrayList<Physical>();

      msgQueue = new ArrayList<CaveMessage>();
   }
   
   public void buildBase(Position position) // tar övre vänstra hörnet
   {
      //FIXME KLAR?
      for (int x = position.x; x < (position.x +6) ; x++)
      {
	 for (int y = position.y; y < (position.y +6) ; y++)
	 {
	    applyAction(new  GameAction( new Position(x,y),ActionClasses.DIGIND,
					 Direction.NONE,null));
	 }
      }
		
      Base base = new Base();
		
      game.addPhysical(position, base);
      game.addPhysical(new Position(position.x + 1, position.y)
		       , base);
      game.addPhysical(new Position(position.x, position.y + 1)
		       , base);
      game.addPhysical(new Position(position.x + 1, position.y + 1)
		       , base);
		
      physicals.add(base);
      game.addTimeDependent(base);
   }
	
   public void  killPhysicals(ArrayList<Physical> deadPhysicals)
   {
      for (Physical P: deadPhysicals)
      {
	 for (int i = 0; i < physicals.size(); i++)
	 {
	    if ( P == physicals.get(i))
	    {
	       physicals.get(i).onDeath(); 
	       //FIXME inte färdigt egentligen
	       physicals.remove(i);
	    }
	 }
      }
   }
	
   public void applyAction(GameAction action)
   {
      switch (action.actionClass()) 
      {
	 case DIG: 
	    dig(action,false);
	    break;

	 case SHOOT: 
	    //TODO 
	    break;

	 case EXPLODE:  
	    //TODO
	    break;

	 case MOVE:
	    if (game.isDug(action.position())
		&& game.gotBig(action.position()))
	    {
	       game.removePhysical(
		  action.position(),
		  action.physical());
	       game.addPhysical(
		  action.position().step(action.direction()),
		  action.physical());
	    }
	    break;

	 case SPAWN: 
	    spawn(action);
	    break;

	 case PICKUP:  
	    if (!game.mapEmpty(action.position().step(
				  action.direction()))
		&& !game.gotBig(action.position().step(
				   action.direction()))
		&& game.isDug(action.position().step(
				 action.direction())))
	    {
				
	       //TODO fyll ut
		       
				
				
	    }
	    break;
	 case WAIT:
	    break;
	 case DIGIND:
	    dig(action,true);
	    break;
	 case COLLAPSE:
	    break;
			
      }
   }
    
   public void dig(GameAction action, boolean indestructable)
   {
      Position position = action.position().step(
	 action.direction());
      if (indestructable)
      {
	 game.digIndestrucable(position, ID);
      }else
      {
	 game.dig(position, ID);
      }
      dugSquares.add(position);
      
      msgQueue.add( new CreateUnitMessage(
		       MessageType.CREATE_UNIT,
		       game.getNewUnitID(),
		       position,
		       Units.HOLE));		       
   }

   public void spawn(GameAction action)
   {
      if (game.isDug(action.position()))
      {
	 physicals.add(action.physical());
	 game.addPhysical( action.position(),
			   action.physical());
	 msgQueue.add(new CreateUnitMessage(
			 MessageType.CREATE_UNIT,
			 game.getNewUnitID(),
			 action.position(),
			 action.physical().getType()));
      }
   }
  
	
   public ArrayList<Position> getEnemies()
   {
      return seenEnemies;
   }

   public boolean gotStraightLine(Position pos1, Position pos2)
   {
      //FIXME kolla att spelaren ser alla rutor mellan också
      return	(pos1.x == pos2.x
		 || pos1.y == pos2.y);
   }
    
   private CaveMessage getMessage()
   {
      return msgOutput.popMessageQueue();
   }
	
   public CaveMessage popMessageQueue()
   {
      if (msgQueue.isEmpty())
      {
	 return new CaveMessage(MessageType.NOMSG);
      }else
      {
	 return msgQueue.get(0);
      }
   }
    
   public void processMessages()
   {
      CaveMessage msg = getMessage();
      
      switch(msg.getType())
      {
	 case CREATE_UNIT_A:
	     //	    game.addPhysical(msg
	    break;
	 default: break;

      }
      
   }

   public void setMessageOutput(MessageOutput msgOutput)
   {
       this.msgOutput = msgOutput;	
   }
}