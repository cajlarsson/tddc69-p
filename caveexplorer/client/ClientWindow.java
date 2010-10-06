package caveexplorer.client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class ClientWindow extends JFrame
{
	private GamePanel panel;
	private ObjectOutputStream msgStream;

    	private class KeyboardListener extends KeyAdapter
	{
		public void keyPressed(KeyEvent e)
		{
			panel.keyPressed(e);
		}
	}
    
	public ClientWindow(ObjectOutputStream msgStream)
	{
		super("Caves are being explored.");
		this.msgStream = msgStream;
		setSize(1200,950);
		
		setLayout(new BorderLayout());
		panel = new GamePanel(80,80,msgStream,null);
		add(panel,BorderLayout.CENTER);
		setFocusableWindowState(true);

		addKeyListener(new KeyboardListener());
		//TODO insert  panels and game area
		setVisible(true);
	}
}