package misc;

import java.util.Date;

public class Log {

	
	public static void outTimed(String str)
	{
		Date dt = new Date();
		System.out.println(dt + ": " + str);
	}
	
	public static void outString(String str)
	{
		System.out.println(str);
	}
}
