package caveexplorer.client;

import java.io.*;
public  class CaveMessage implements Serializable
{
    private MessageType type;
    
    public CaveMessage(MessageType type)
    {
	this.type = type;
    }
    
	public MessageType getType()
    {
	return type;
    }
    
}
