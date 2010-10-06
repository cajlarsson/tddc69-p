package caveexplorer.client;

import java.io.*;
public class UnitMessage extends CaveMessage
{
	private int ID;
	public UnitMessage(MessageType type, int ID)
	{
		super(type);
		this.ID = ID;
	}
	
	public int getID()
	{
		return ID;
	}
}
