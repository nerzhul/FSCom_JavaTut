package socket;

import java.net.*;
import java.io.*;
public class sender extends Thread
{
	final static int port = 5677;
	final static String IP = "192.168.1.12";

	Socket socket;

	public sender()
	{
		
	}
	
	public void run()
	{
		try
		{
			// create a socket on mine IP.
		    socket = new Socket(IP,port);
		    
		    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		    String line = reader.readLine();
		    while (line != null) 
		    {
		    	
		    	SendPacket(line);
		    	System.out.println("echo: " + reader.readLine());
		    	line = reader.readLine();

		    }
		    // don't forget to close the socket or client get an internal error !
		    socket.close();

		} catch (Exception e) 
		{
	      e.printStackTrace();
	    }
	}
	
	private void SendPacket(String packt) throws IOException
	{
		// creating buffers for packets to send
		PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
	    
		// send packet
    	out.println(packt);
	}

}
