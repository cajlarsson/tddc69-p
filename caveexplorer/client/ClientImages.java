package caveexplorer.client;
import java.util.*;
import java.awt.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

public  class ClientImages
{
	private static final ClientImages instace = new ClientImages();
	ArrayList<Image> images;	
	
	private ClientImages()
	{
		images = new ArrayList<Image>();
		
		try
		{
			images.add(ImageIO.read(
					   new File("asd")));
			images.add(ImageIO.read(
					   new File("asd")));
			images.add(ImageIO.read(
					   new File("asd")));
			images.add(ImageIO.read(
					   new File("asd")));
			images.add(ImageIO.read(
					   new File("asd")));
			images.add(ImageIO.read(
					   new File("asd")));
		}catch (IOException e)
		{
			int i;
		}
	}
	
	public static Image getImage(Images image)
	{
		return null;
	}
}