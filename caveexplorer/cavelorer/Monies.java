package caveexplorer.cavelorer;

public class Monies implements Tickable
{
   private	int amount = 0;
	
   public void preformTick()
   {
      amount++;
      
   }
	
   public void change(int amount)
   {
      this.amount += amount;
   }
	
   public int get()
   {
      return amount;
   }

   public boolean deduct(int amount)
   {
      if ( this.amount >= amount)
      {
	 this.amount -= amount;
	 return true;
      }
      return false;
   }
}
