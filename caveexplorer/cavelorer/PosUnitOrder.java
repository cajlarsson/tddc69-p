package caveexplorer.cavelorer;

public class PosUnitOrder extends GameOrder
{
   private Position position; 
   private int unitID;
   
   public PosUnitOrder(Position position, int unitID,OrderClasses orderClass )
   {
      super(orderClass);
      this.position = position;
      this.unitID = unitID;
   }
   
   public Position getPosition()
   {
      return position;
   }
   
   public int getID()
   {
      return unitID;
   }
} 