package caveexplorer.client;

public abstract class Util
{
	public static Layers getLayer(Units unit)
	{
		switch (unit)
		{
		case MY_SOLDIER :
		case ENEMY_SOLDIER :
		case B_BOMB:
		case ENEMY_BOMB:  return Layers.UNIT;
		case S_BOMB:
		case MINE: return Layers.FLOOR;
		case HOLE: return Layers.DIG;
		}	
		return null;
	}

	public static Images getImage(Units unit)
	{
		switch (unit)
		{
		case MY_SOLDIER : return Images.SOLDIER_UP;
		case ENEMY_SOLDIER : return Images.SOLDIER_DOWN;
		case B_BOMB: return Images.BIG_BOMB;
		case ENEMY_BOMB:  return Images.SOLDIER_LEFT;
		case S_BOMB: return Images.SMALL_BOMB;
		case MINE: return Images.MINE;
		case HOLE: return Images.HOLE;
		}
		return null;
	}
}