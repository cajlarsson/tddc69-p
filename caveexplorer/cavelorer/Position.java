package caveexplorer.cavelorer;

import java.util.*;

public class Position
{
   public	int x,y;
   public Position(int x, int y)
   {
      this.x = x;
      this.y = y;
   }
   public Position step(Direction direction)
   {
      switch (direction)
      {
	 case UP: return new Position(x, y - 1); 
	 case RIGHT: return new Position(x + 1, y);
	 case DOWN: return new Position(x, y + 1); 
	 case LEFT: return new Position(x - 1, y); 
	 case NONE: return this;
      }
      return null; //IMPOSSIBLE + SILENT FAIL
   }
	
   public Position add(Position position)
   {
      return new Position(x + position.x,
			  y + position.y);
   }
	
   public Position subtract(Position position)
   {
      return new Position(x - position.x,
			  y - position.y);
   }
	
   public double distance(Position position)
   {
      Position temp = subtract(position);
      return Math.sqrt(temp.x * temp.x +
		       temp.y * temp.y);
   }
   
   public boolean equal(Position position)
   {
      return (x == position.x && y == position.y);
   }

   public String stringOf()
   {
      return  "x: " +String.valueOf(x) +",y: " +String.valueOf(x);
   }
}