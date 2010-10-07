package caveexplorer.client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class ClientWindow extends JFrame
{
	private GamePanel panel;
	private ObjectOutputStream msgOutStream;

    	private class KeyboardListener extends KeyAdapter
	{
		public void keyPressed(KeyEvent e)
		{
			panel.keyPressed(e);
		}
	}
    
	public ClientWindow(OutputStream msgOutStream,
			    InputStream msgInputStream)
	{
		super("Caves are being explored.");
		this.msgOutStream = msgOutStream;
		setSize(1200,950);
		
		setLayout(new BorderLayout());
		panel = new GamePanel(80,80,msgOutStream,null);
		add(panel,BorderLayout.CENTER);
		setFocusableWindowState(true);

		addKeyListener(new KeyboardListener());
		//TODO insert  panels and game area
		setVisible(true);
	}
}