package misc;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class Log {

	private static String logfile = "Server.log";
	private static String errorlogfile = "Error.log";
	
	public static void outTimed(String str)
	{
		Date dt = new Date();
		System.out.println(dt + ": " + str);
	}
	
	public static void outString(String str) throws IOException
	{
		System.out.println(str);
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
			  writer.close();
		}

		
	}
	
	public static void outError(String str) throws IOException
	{
		System.err.println(str);
		FileWriter writer = null;
		try
		{
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
			  writer.close();
		}
	}
}
