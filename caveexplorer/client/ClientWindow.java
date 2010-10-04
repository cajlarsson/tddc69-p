package caveexplorer.client;

import javax.swing.*;
import java.awt.*;

public class ClientWindow extends JFrame
{
	public ClientWindow()
	{
		super("Caves are being explored.");
		setSize(1200,950);
		setLayout(new BorderLayout());
		add(new GamePanel(80,80),BorderLayout.CENTER);
		//TODO insert  panels and game area
		setVisible(true);
	}
}