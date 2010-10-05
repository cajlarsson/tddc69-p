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
	
	public void paintLayer(Graphics g, JComponent owner)
	{
		for (Paintee P : this)
		{
			P.Paint(g,owner);
		}
	}
}