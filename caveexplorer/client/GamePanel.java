package caveexplorer.client;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.*;
import java.util.ArrayList;
import caveexplorer.cavelorer.*;
import java.awt.color.*;
import java.io.*;

public class GamePanel extends JComponent implements MessageOutput,
					  ActionListener
{
   private ArrayList<MapLayer> layers;
   private ArrayList<MovableUnit> movableUnits;
   private ArrayList<CaveMessage> msgQueue;
   private char pressedKey = 'x';
   
   private int selectedUnit;
   
   private Paintee selectedPaintee;
   
   private MessageOutput msgOutput;
   
   private Timer tickTimer;
   
   private int cash = 0;

   private class ClickListener extends MouseAdapter 
   {
	
      public void mouseClicked(MouseEvent ev) 
      {
	 
      }
      
      public void mousePressed(MouseEvent ev)
      {
	 switch(pressedKey)
	 {
	    case 'w' :
	    case 'a' :

	    case 'd' : 	       
		  
	    case 's' :
	       msgQueue.add(	  
		  new UnitMessage(MessageType.ABORT,selectedUnit));		     
	    break;   
		
	    case 'l' : //create new unit: buggtesttool, FIXME! REMOVE THIS CODE
	       msgQueue.add(
		  new PosUnitMessage(
		     MessageType.CREATE_UNIT_A,
		     0,
		     new Position(ev.getX() / 10, 
				  ev.getY() / 10)));
	    default:
	       selectUnit(ev.getX() / 10, ev.getY() / 10);
	       
	       break;
		    
	 }
      }
   }
    
    
   public GamePanel(int width, int height)

   {
      msgQueue = new ArrayList<CaveMessage>();

      layers = new ArrayList<MapLayer>();
	
      movableUnits = new ArrayList<MovableUnit>();
      layers.add(generateBottomLayer(width,height)); //DIRT
      layers.add(new MapLayer()); //DIG
      layers.add(new MapLayer());//FLOOR
      layers.add(new MapLayer()); //UNIT
      layers.add(new MapLayer());//PROJECTILES		
	
      addMouseListener(new ClickListener());
      
      tickTimer = new Timer(100, this); 
      tickTimer.start();
   }
    
   public void actionPerformed(ActionEvent e)
   {
      while(true)
      {
	 CaveMessage temp = getMessage();
	 if (temp.getType() == MessageType.NOMSG)
	 {
	    break;
	 }else
	    pushMessage(temp);
      }
      //doTick();
   }
   
   public void paintComponent(Graphics g)
   {
      g.setColor(Color.red);
      g.fillRect(0,0,this.getWidth( ), this.getHeight());
	
      for (MapLayer l : layers)
      {
	 l.paintLayer(g, this);
      }
      
      g.setFont(new Font("Terminus",0,10));
      g.setColor(Color.BLACK);
      g.drawString("MONIES: "+ String.valueOf(cash),10,10);
      
   }
   
   private MapLayer generateBottomLayer(int width, int height)
   {
      BufferedImage img = new BufferedImage(width*10, height*10,
					    BufferedImage.TYPE_INT_RGB);
      img.createGraphics();
      Graphics2D g = (Graphics2D)img.getGraphics();
      g.setColor(new Color(0xAA660F));
      g.fillRect(0, 0, width * 10, height * 10);
	
      MapLayer temp = new MapLayer();
      temp.add(new PaintCustomImage(img, new Position(0,0)));
	
      return temp;		
   }
    
   public void addUnit(Units unitType, Position position, int ID)
   {
      movableUnits.add( new MovableUnit(layers,
					unitType,
					position,
					ID));
      System.out.print(String.valueOf(unitType.ordinal()));
      repaint();
   }
    
   public void addUnit(CreateUnitMessage msg)
   {
      addUnit(msg.getUnits(),
	      msg.getPosition(),
	      msg.getID());
   }

   public void removeUnit(int ID)
   {
      for (int i = 0 ; i < movableUnits.size(); i++)
      {
	 if ( ID == movableUnits.get(i).getID())
	 {
	    movableUnits.get(i).removeImage();
	    movableUnits.remove(i);				
	 }
      }
      repaint(); //FIXME  temprorary
   }
   public MovableUnit getUnit(int ID)
   {
      for (MovableUnit M : movableUnits)
      {
	 if (M.getID() == ID)
	 {
	    return M;
	 }
      }
      return null;
   }

   public void keyPressed(KeyEvent e)
   {
      switch (e.getKeyChar())
      {
	 case 'a':
	 case 's':
	 case 'd':
	 case 'w':
	    pressedKey = e.getKeyChar();
	 break;
	 default:
	    pressedKey = 'x';
	    break;
      }
      //removeUnit(++D);
   }
   public void keyReleased(KeyEvent e)
   {
      //	removeUnit(++D);
      pressedKey = 'x';
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
   
   public void pushMessage(CaveMessage msg)
   {
      System.out.print("new message: ");
      
	
      switch (msg.getType())
      {
	 case ORDER: 
	    break; // fel riktning
	 case MOVE: 
	    getUnit( ((MoveUnitMessage)msg).getID()).moveTo(
	       ((MoveUnitMessage)msg).getPosition());
	    break; 
	 case IMPOSSIBLE_ORDER: 
	    break; 
	 case CREATE_UNIT:
	    System.out.print("CREATE\n");
	    addUnit((CreateUnitMessage)msg);
	    break; 
	 case KILL: 
	    removeUnit(((UnitMessage)msg).getID());
	    break;
	 case SHOOT: 
	    break; 
	 case SETCASH:
	    cash = ((UnitMessage)msg).getID(); //misuse
	    repaint();
	    break;
	 default: 
	    System.out.print("OTHER\n");
	    break;
      }
   }
   

   
   public void setMessageOutput(MessageOutput msgOutput)
   {
      this.msgOutput = msgOutput;
   }
   
      private void selectUnit( int x, int y)
   {
      Position clickedPos = new Position(x,y);
      //   for (MovableUnit M : 
   }

}
