package caveexplorer.client;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import caveexplorer.cavelorer.*;
import java.awt.image.*;
public class PaintImage extends Paintee
{
	private Position position;
	private Images image;
	
	public PaintImage(Images image, Position position)
	{
		super(position);
		this.image = image;
	
	}

	public void Paint(Graphics g)
	{
		g.drawImage( ClientImages.getImage(image),
			     position.x,
			     position.y,
			     new ImageObserver()
			     {
				     public  boolean imageUpdate(Image img,
						 int infoflags, 
						 int x, int y,
						 int width,
						 int height)
 				     {
					     return true;
				     }
			     });
	}
				
	public void cwRotate()
	{
		//FIXME IMPLEMENT
	}
	
	public void acwRotate()
	{
	}
	
	public void setImage(Images image)
	{
		this.image = image;
	}
	
}