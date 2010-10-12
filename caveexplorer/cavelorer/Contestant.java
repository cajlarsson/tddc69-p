package caveexplorer.cavelorer;

import java.util.*;
import caveexplorer.client.*;
import java.io.*;

public class Contestant implements MessageOutput,Tickable
{
   private ArrayList<Physical> physicals;
   //	private int base; //FIXME;
   private ArrayList<Position> knownSquares; //FIXME;
   private ArrayList<Position> dugSquares;
   private ArrayList<Position> seenEnemies;
   private ArrayList<CaveMessage> msgQueue;

   private Monies money;
      
   private MessageOutput msgOutput;
   
   private int ID;
      
   private GameEngine game;

   public Contestant(GameEngine game, int ID)
   {
      this.game = game;
      this.ID = ID;
               
      money = new Monies();
      game.addTimeDependent(money);
      game.addTimeDependent(this);      
		
      dugSquares = new ArrayList<Position>();

      physicals = new ArrayList<Physical>();

      msgQueue = new ArrayList<CaveMessage>();
   }
   
   public void buildBase(Position position) // tar övre vänstra hörnet
   {
      //FIXME KLAR? IGEN, KLAR?
      for (int x = position.x - 3; x < (position.x +5) ; x++)
      {
	 for (int y = position.y - 3 ; y < (position.y +5) ; y++)
	 {
	    applyAction(new  GameAction( new Position(x,y),ActionClasses.DIGIND,
					 Direction.NONE,null));
	 }
      }
		
      Base base = new Base(ID,game.getNewUnitID());
      applyAction( new GameAction(position,ActionClasses.SPAWN,
				  Direction.NONE,base));
      applyAction( new GameAction(new Position(position.x + 1, position.y),
				  ActionClasses.SPAWN,Direction.NONE,base));
      applyAction( new GameAction(new Position(position.x, position.y + 1),
				  ActionClasses.SPAWN,Direction.NONE,base));
      applyAction( new GameAction(new Position(position.x + 1, position.y + 1),
				  ActionClasses.SPAWN, Direction.NONE,base));
      physicals.add(base);

      Soldier soldier = new Soldier(this,ID, game.getNewUnitID());

      applyAction( new GameAction(new Position(position.x , position.y + 2),
				  ActionClasses.SPAWN, Direction.NONE,soldier));
      physicals.add(soldier);
      
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
   public  void preformTick()
   {
      msgQueue.add(new UnitMessage(MessageType.SETCASH,money.get()));	      
   }
   public void move(GameAction action)
   {
      if (game.isDug(action.position())
	  && !game.gotBig(action.position()))
      {
	 game.removePhysical(
	    game.findPhysical(action.physical()),
	    action.physical());
	 game.addPhysical(
	    action.position(),
	    action.physical());
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
			 action.physical().getID(),
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
	 CaveMessage temp = msgQueue.get(0);
	 msgQueue.remove(0);
	 return temp;
      }
   }
   
   private Physical getPhysical(int ID)
   {
      for (int i = 0; i < physicals.size(); i++)
      {
	 if ( ID == physicals.get(i).getID())
	 { 
	    return physicals.get(i);
	 }
      } 
      return null;
   }
   
   public void processMessages()
   {
      CaveMessage msg = getMessage();
  
      switch(msg.getType())
      {
	 
	 case MOVE_A:
	    move(new GameAction(((MoveUnitMessage)msg).getPosition(),
				ActionClasses.MOVE, 
				getPhysical(((MoveUnitMessage)msg).getID())));
	    break;
	 case CREATE_UNIT_A:
	    spawn(new GameAction(((CreateUnitMessage)msg).getPosition(),
				 ActionClasses.SPAWN,
				 new Soldier(this, ID,
					      ((CreateUnitMessage)msg)
					      .getID())));
	 break;
	 default: break;
      }
   }
   
   public void setMessageOutput(MessageOutput msgOutput)
   {
      this.msgOutput = msgOutput;	
   }
}