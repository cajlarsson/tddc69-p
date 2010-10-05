package caveexplorer.client;

import java.util.*;
import java.awt.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

public  class ClientImages
{
	private static final ClientImages instace = new ClientImages();
	static	ArrayList<Image> images;	
	
	private ClientImages()
	{
		images = new ArrayList<Image>();
		String path = new String("caveexplorer/client/pics/");
		try
		{
			images.add(ImageIO.read(
					   new File(path + "loldiern.bmp")));
			images.add(ImageIO.read(
					   new File(path +"loldiere.bmp")));
			images.add(ImageIO.read(
					   new File(path +"loldiers.bmp")));
			images.add(ImageIO.read(
					   new File(path +"loldierw.bmp")));
			images.add(ImageIO.read(
					   new File(path + "UndugDirtBg.bmp")));
			images.add(ImageIO.read(
					   new File(path + "dugDirtBg.bmp")));
			images.add(ImageIO.read(
					   new File(path + "mine.bmp")));
			images.add(ImageIO.read(
					   new File(path + "smallbomb.bmp")));
			images.add(ImageIO.read(
					   new File(path + "smallbomb.bmp")));
			
		}catch (IOException e)
		{
			int i;
		}
	}
	
	public static Image getImage(Images image)
	{
		return  images.get(image.ordinal());
	}
}