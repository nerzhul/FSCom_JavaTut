package misc;

public class Misc {

	public static boolean isWrongType(Object obj,Object obj2)
	{
		if(obj == null || obj.getClass().equals(obj2.getClass()))
			return false;
		else
			return true;
	}
}
