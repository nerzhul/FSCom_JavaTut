package socket;

import java.net.*;
import java.io.*;
public class sender extends Thread
{
	final static int port = 21;

	Socket socket;
    DataInputStream userInput;

	public sender()
	{
		
	}
	
	public void run()
	{
		try
		{
		    socket = new Socket("192.168.1.12",port);

		} catch (Exception e) 
		{
	      e.printStackTrace();
	    }

	}

}
