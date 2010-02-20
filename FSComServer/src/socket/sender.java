package socket;

import java.net.*;
import java.io.*;

import socket.packet.Packet;

import misc.Log;

public class Sender
{
	private Socket socket;
	private Packet pck;
	public Sender(Socket sock,Integer op,Object packet)
	{
		socket = sock;
		pck = new Packet(op,packet);
	}

	public void CloseConnection()
	{
		try 
		{
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

		try 
		{
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
