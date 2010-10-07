package caveexplorer.cavelorer;

import java.io.*;

public class ObjectInterface
{
	private OutputStream out;
	private InputStream in;
	
	public ObjectInterface( OutputStream out,
				InputStream in)
	{
		this.in = in;
		this.out = out;
	}
	
	public OutputStream getOut()
	{
		return out;
	}
	
	public InputStream getIn()
	{
		return in;
	}
}