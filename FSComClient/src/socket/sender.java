package socket;

import java.net.*;
import java.io.*;

import misc.Log;
import socket.packet.*;

public class sender extends Thread
{
	final static int port = 5677;
	final static String IP = "127.0.0.1";

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
		    while (true) 
		    {
		    	SendPacket(0xFF,line);
		    	if(line.equals("close"))
		    		break;
		    	line = reader.readLine();
		    }
		    // don't forget to close the socket or client get an internal error !
		    
		    Log.outString("Close connection with server !");
		    socket.close();

		} catch (Exception e) 
		{
	      e.printStackTrace();
	    }
	}
	
	private void SendPacket(Integer opcode,Object packt) throws IOException
	{
		// creating buffers for packets to send
		PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

		// send packet
    	out.println("0x" + Integer.toHexString(opcode) + packt);
	}

}
