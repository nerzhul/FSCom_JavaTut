package misc;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

import javax.swing.JOptionPane;

public class Log {

	private static String errorlogfile = "Error.log";
	
	public static void outTimed(String str)
	{
		Date dt = new Date();
		System.out.println(dt + ": " + str);
	}
	
	public static void outString(String str)
	{
		System.out.println("DEBUG:" + str);
	}
	
	public static void outError(String str)
	{
		Date dt = new Date();
		str = dt + ": " + str;
		System.err.println(str);
		str += "\r\n";
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
			try {
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void outPrompt()
	{
		System.out.print("Command> ");
	}
	
	public static void ShowPopup(String text,String title,boolean error)
	{
		JOptionPane.showMessageDialog(null, text,title,error ? JOptionPane.ERROR_MESSAGE : JOptionPane.INFORMATION_MESSAGE);
		if(error)
			outError(text);
	}
}
