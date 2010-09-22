package cavelorer;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;


public class Dirt implements Tickable
{
	boolean dug = false;
	boolean foreverz = false;
	int timeToLive = -1;
	int dugBy = -1;

	public void preformTick()
	{
		if(dug)
		{
			timeToLive--;
		}
		if ((timeToLive < 0 )
		    &&!foreverz)
		{
			dug = false;
			//FIXME Kollapsa;
		}
		
		
		
	}



	
}
