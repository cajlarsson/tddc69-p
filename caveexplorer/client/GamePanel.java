package caveexplorer.client;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.*;
import java.util.*;
import caveexplorer.cavelorer.*;
import java.awt.color.*;
import java.io.*;

public class GamePanel extends JComponent
{
    private ArrayList<MapLayer> layers;
    private ArrayList<MovableUnit> movableUnits;
    private ObjectOutputStream msgOutStream;
    private ObjectInputStream msgInStream;

    private char pressedKey = 'x';
    
    static int D = 0;  //FIXME clean this shit
    
    static int I = 0;   //FIXME clean this shit
    
    
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
		    try 
			{
			    msgOutStream.writeObject(
			       new PosUnitMessage(
						  MessageType.HALT,
						  0,
						  new Position(ev.getX() / 10, 
							       ev.getY() / 10)));
			}catch (IOException e)
			{
			    System.out.println("OBJEKTFEL DIN IDIOT");
			    
			}catch (NullPointerException e)
			{
			    System.out.println("OBJEKTFEL2 DIN IDIOT");
			}
		break;   
		
		case 'l' : //create new unit: buggtesttool, FIXME! REMOVE THIS CODE
		    try 
			{
			    msgOutStream.writeObject(
			       new PosUnitMessage(
						  MessageType.CREATE_UNIT_A,
						  0,
						  new Position(ev.getX() / 10, 
							       ev.getY() / 10)));
			}catch (IOException e)
			{
			    System.out.println("OBJEKTFEL DIN IDIOT");
			    
			}catch (NullPointerException e)
			{
			    System.out.println("OBJEKTFEL2 DIN IDIOT");
			}
		    break;
		default:
		    System.out.println("clicky"); //FIXME REMOVE
		    break;
		    
		}
	}
    }
    
    
    public GamePanel(int width, int height, 
		     OutputStream msgOutStream,
		     InputStream msgInStream)
    {
	this.msgOutStream = new ObjectOutStream(msgOutStream);
	this.msgInStream = new ObjectInStream(msgInStream);
	
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
	BufferedImage img = new BufferedImage(width*10, height*10,
					      BufferedImage.TYPE_INT_RGB);
	img.createGraphics();
	Graphics2D g = (Graphics2D)img.getGraphics();
	g.setColor(new Color(0xCC6600));
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
	repaint();
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
   
    public void pushPacket(CaveMessage msg)
    {
	
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
		addUnit(((CreateUnitMessage)msg).getUnits(),
			((CreateUnitMessage)msg).getPosition(),
			((CreateUnitMessage)msg).getID());
		break; 
	    case KILL: 
		removeUnit(((UnitMessage)msg).getID());
		break;
	    case SHOOT: 
		break; 
	    default: break;
	    }
    }
    
}
