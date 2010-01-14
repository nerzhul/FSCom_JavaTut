package socket;

import java.net.*;
import java.io.*;

public class sender extends Thread
{

	private Socket socket;
	private String IP;
	public sender(String ip)
	{
		IP = ip;
	}
	
	public void run()
	{
		try
		{
			// create a socket on mine IP.
		    socket = new Socket(IP,0);

		} 
		catch (Exception e) 
		{
	      e.printStackTrace();
	    }
	}
	
	public void CloseConnection() throws IOException
	{
		socket.close();
	}
	
	public void SendPacket(Integer opcode,Object packt) throws IOException
	{
		// creating buffers for packets to send
		PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

		// send packet
    	out.println("0x" + Integer.toHexString(opcode) + packt);
	}

}
