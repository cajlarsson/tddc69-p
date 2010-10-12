package caveexplorer;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.imageio.*;
import java.io.*;

import caveexplorer.client.*;
import caveexplorer.cavelorer.*;

public class CaveWindow extends JFrame
{
         
    private class OurButton extends JButton     
    {
        
        public OurButton(String name)
        {
            super(name);
            this.setFont(new Font("Termnius",0, 12));
        }
    }   
                
    private class JoinButton extends OurButton
    {
        public JoinButton()
        {
            super("Join Game");
                                                
            this.addActionListener( new ActionListener()
                {
                    public void actionPerformed(ActionEvent e)
                    {
			//   new ClientWindow(null,null);
                    }
                });
        }
    }
                
    private class HostButton extends OurButton
    {
        public HostButton()
        {
            super("Host Game");
                                                
            this.addActionListener( new ActionListener()
                {
                    public void actionPerformed(ActionEvent e)
                    {
                        //TODO skapa hostwindow
                    }
                });
        }
    }
                
    private class SingleButton extends OurButton
    {
        public SingleButton()
        {
	    super("Single Game");
                                                
            this.addActionListener( new ActionListener()
                {
                    public void actionPerformed(ActionEvent e)
		    {
			int width = 80;
			int height = 80;
			ClientWindow client1 = new ClientWindow(width,height);
			ClientWindow client2 = new ClientWindow(width,height);
			
			GameEngine engine = new GameEngine(width,height);

			engine.addContestant( new Position(10, height/2),
					      client1);
			engine.addContestant( new Position(width -10, height/2),
					      client2);		
			engine.start();
		    }
                });
        }
    }
                
    private class ExitButton extends OurButton
    {
        public ExitButton()
        {
            super("Exit");
                                                
            this.addActionListener( new ActionListener()
                {
                    public void actionPerformed(ActionEvent e)
                    {
                        System.exit(0);
                    }
                });
        }
    }
                
    private class CaveImagePanel extends JPanel
    {
        private BufferedImage image;
                                
        public CaveImagePanel() 
        {
            try
                {
                    image = ImageIO.read(
                                         new File("caveexplorer/cave5.jpg"));
                } catch (IOException ex) 
                {
                    System.exit(1);
                }
        }
                                
        public void paintComponent(Graphics g) 
        {
            g.drawImage(image, 0, 0, null);                     
        }
    }
                
    public CaveWindow()
    {
        super("Cave Explorer");
                                
        setLayout(new BorderLayout());
                                
        setSize(600,400);
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout( new BoxLayout( buttonPanel
					      ,BoxLayout.X_AXIS));
	//    buttonPanel.add(Box.createHorizontalGlue());
	buttonPanel.add(new SingleButton(),buttonPanel);
	buttonPanel.add(new JoinButton());
	buttonPanel.add(new HostButton());
	buttonPanel.add(new ExitButton(),buttonPanel);
	add(buttonPanel,BorderLayout.SOUTH);
	add(new CaveImagePanel(), BorderLayout.CENTER);
	setVisible(true);
    }
}
