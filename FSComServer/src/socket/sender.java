package socket;

import java.net.*;
import java.io.*;

import socket.packet.packet;

import misc.Log;

public class sender
{
	private Socket socket;
	private packet pck;
	public sender(Socket sock,Integer op,Object packet)
	{
		socket = sock;
		pck = new packet(op,packet);
	}
	
	public void CloseConnection()
	{
		try {
			
			socket.close();
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public void SendPacket()
	{
		ObjectOutputStream out;

		try {
			out = new ObjectOutputStream(new BufferedOutputStream(socket.getOutputStream()));
			// send packet
			out.writeObject(pck);
			out.flush();
	    	Log.outTimed("Send packet " + pck.getOpcode() + ": " + pck.getData() + " to client : " + socket.getInetAddress());
		
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
}
