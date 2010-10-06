package caveexplorer.client;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import caveexplorer.cavelorer.*;
import java.awt.image.*;
public class PaintCustomImage extends Paintee
{
	private Position position;
	private Image image;
	
	public PaintCustomImage(Image image, Position position)
	{
		super(position);
		this.image = image;
	
	}

	public void Paint(Graphics g, JComponent owner)
	{
	   		g.drawImage( image,
				     getPosition().x*10,
				     getPosition().y*10,
				     owner);
	}
				
	public void cwRotate()
	{
		//FIXME IMPLEMENT
	}
	
	public void acwRotate()
	{
	}
	
	public void setImage(Image image)
	{
		this.image = image;
	}
	
	public void setPosition(Position position)
	{
		this.position = position;
	}
}