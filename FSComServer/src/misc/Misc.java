package misc;

/*
 * Miscellaneous functions
 */
public class Misc {

	// Verify if type of two objects if the same
	public static boolean isWrongType(Object obj,Object obj2)
	{
		if(obj == null || obj.getClass().equals(obj2.getClass()))
			return false;
		else
			return true;
	}
}
