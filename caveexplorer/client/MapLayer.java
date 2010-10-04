package caveexplorer.client;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class MapLayer extends ArrayList<Paintee>
{
	public MapLayer()
	{
		super();
	}
	
	public void PaintLayer(Graphics g)
	{
		for (int i = 0; i < size() ; i++)
		{
			get(i).Paint(g);
		}
	}
}