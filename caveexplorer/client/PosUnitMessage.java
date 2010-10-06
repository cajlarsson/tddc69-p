package caveexplorer.client;

import java.io.*;
import caveexplorer.cavelorer.*;
public class PosUnitMessage extends UnitMessage
{
	private Units unitType;
	private Position position;
	public PosUnitMessage(MessageType type, int ID,
				 Position position)
	{
		super(type, ID);
		this.position = position;
		this.unitType = unitType;
	}
	
	public Position getPosition()
	{
		return position;
	}
}