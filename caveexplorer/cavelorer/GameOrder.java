package caveexplorer.cavelorer;

public class GameOrder
{
    private OrderClasses orderClass;
    
    public GameOrder( OrderClasses orderClass)
    {
	this.orderClass = orderClass;
    }
    
    public OrderClasses getType()
    {
	return orderClass;
    }
} 