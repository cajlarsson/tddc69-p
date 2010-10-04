package caveexplorer.client;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import caveexplorer.cavelorer.*;

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
		g.setColor(Color.PINK);
		g.fillRect(position.x, position.y, 10,10);
	}
				
	public void cwRotate()
	{
		//FIXME IMPLEMENT
	}
	
	public void acwRotate()
	{
	}
		
	
}