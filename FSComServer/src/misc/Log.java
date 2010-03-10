package misc;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

/*
 * Log class
 */
public class Log {

	private static String logfile = "Server.log";
	private static String errorlogfile = "Error.log";
	
	public static void outTimed(String str)
	{
		if(Config.getLoglevel() < 3)
			return;
		
		Date dt = new Date();
		System.out.println(dt + ": " + str);
	}
	
	public static void outString(String str)
	{
		if(Config.getLoglevel() < 2)
			return;
		System.out.println(str);
		str += "\r\n";
		FileWriter writer = null;
		try
		{
		     writer = new FileWriter(logfile, true);
		     writer.write(str,0,str.length());
		}
		catch(IOException ex)
		{
		    ex.printStackTrace();
		}
		finally
		{
		  if(writer != null)
		  {
				try {
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		  }
		}
	}
	
	public static void outError(String str)
	{
		if(Config.getLoglevel() < 1)
			return;
		
		FileWriter writer = null;
		try
		{
			Date dt = new Date();
			str = dt + ": " + str;
			System.err.println(str);
			str += "\r\n";
			
		    writer = new FileWriter(errorlogfile, true);
		    writer.write(str,0,str.length());
		}
		catch(IOException ex)
		{
		    ex.printStackTrace();
		}
		finally
		{
		  if(writer != null)
			try {
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static int getErrorCode(error_codes i)
	{
		return i.getValue();
	}
}
