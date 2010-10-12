package caveexplorer.client;

import java.io.*;
import caveexplorer.cavelorer.*;
public class OrderMessage extends CaveMessage
{
   private GameOrder order;
   public OrderMessage(GameOrder order)
   {
      super(MessageType.ORDER);
      this.order = order;
   }
   
   public GameOrder getOrder()
	{
	   return order;
	}
}
