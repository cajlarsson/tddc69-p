package caveexplorer.client;

import caveexplorer.cavelorer.*;
import java.io.*;

public class MoveUnitMessage extends UnitMessage
{
	private Units unitType;
	private Position position;
	public MoveUnitMessage(MessageType type, int ID,
				 Position position, Units unitType)
	{
		super(type, ID);
		this.position = position;
		this.unitType = unitType;
	}
	
	public Position getPosition()
	{
		return position;
	}
	
	public Units getUnits()
	{
	    return unitType;
	}
}