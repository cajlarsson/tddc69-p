package caveexplorer.client;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import caveexplorer.cavelorer.*;
import java.awt.image.*;
public class PaintImage extends Paintee
{
    private Images image;
	
    public PaintImage(Images image, Position position)
    {
	super(position);
	this.image = image;
    }

    public void Paint(Graphics g, JComponent owner)
    {
	g.drawImage( ClientImages.getImage(image),
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
	
    public void setImage(Images image)
    {
	this.image = image;
    }
} 