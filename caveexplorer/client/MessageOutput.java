package caveexplorer.client;

public interface MessageOutput
{
    public CaveMessage popMessageQueue();
    public void setMessageOutput(MessageOutput msgOutput);
}