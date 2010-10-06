package caveexplorer.client;

import java.io.*;
import caveexplorer.cavelorer.*;
public class CreateUnitMessage extends UnitMessage
{
	private Units unitType;
	private Position position;
	public CreateUnitMessage(MessageType type, int ID,
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