package socket;

import java.net.*;
import java.io.*;
public class sender extends Thread
{
	final static int port = 5677;
	final static String IP = "192.168.1.12";

	Socket socket;
    DataInputStream userInput;

	public sender()
	{
		
	}
	
	public void run()
	{
		try
		{
			// create a socket on mine IP.
		    socket = new Socket(IP,port);
		    
		    // don't forget to close the socket or client get an internal error !
		    socket.close();

		} catch (Exception e) 
		{
	      e.printStackTrace();
	    }

	}

}
