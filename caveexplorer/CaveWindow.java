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
                        new ClientWindow(null,null);
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
			try
			    {
			    
			    PipedInputStream inStream1 = new PipedInputStream();    
			    PipedOutputStream outStream1 = new PipedOutputStream(
										 inStream1);
			    
			    PipedInputStream inStream2 = new PipedInputStream();    
			    PipedOutputStream outStream2 = new PipedOutputStream(
										 inStream2);
			    
			    ArrayList<ObjectInterface> interfaces = new 
				ArrayList<ObjectInterface>();
			    
			    interfaces.add(new ObjectInterface(
							       outStream2,
							       inStream1));
			    
			    new GameEngine(100,100,
					   interfaces);
			
			    new ClientWindow(outStream1,inStream2); 
			    }catch(IOException exception)
			    {
				System.out.print("something failed in creating streams");
			    }
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
