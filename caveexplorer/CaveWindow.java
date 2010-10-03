
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.imageio.*;
import java.io.*;

public class CaveWindow extends JFrame{

	private class OurButton extends JButton	{

		public OurButton(String name)
		{
			super(name);
			this.setFont(new Font("Monospace",0, 12));
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
					//TODO skapa joinwindow
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
					//TODO starta spel med ai
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
				image = ImageIO.read(new File("cave5.jpg"));
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
