package caveexplorer.client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class ClientWindow extends JFrame implements MessageOutput
{
    private GamePanel panel;
    
    private class KeyboardListener extends KeyAdapter
    {
	public void keyPressed(KeyEvent e)
	{
	    panel.keyPressed(e);
	}
    }
    
    public ClientWindow(int width, int height)
    {
	super("Caves are being explored.");
	setSize(1200,950);
	
	setLayout(new BorderLayout());
	panel = new GamePanel(width,height);
	add(panel,BorderLayout.CENTER);
	setFocusableWindowState(true);
	
	addKeyListener(new KeyboardListener());
	//TODO insert  panels and game area
	setVisible(true);
    }
    
    public CaveMessage popMessageQueue()
    {
	return panel.popMessageQueue();
    }
    
    public void setMessageOutput(MessageOutput msgOutput)
    {
	panel.setMessageOutput(msgOutput);
    }
	
}