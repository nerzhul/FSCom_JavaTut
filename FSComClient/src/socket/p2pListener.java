package socket;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.SocketTimeoutException;

import socket.packet.Packet;
import socket.packet.Packet_handler;

import misc.Log;

public class p2pListener extends Thread
{
	private Socket sock;
	ObjectInputStream in;
	
	public p2pListener(Socket skt) 
	{
		sock = skt;
	}
	
	public void run()
	{
		try
		{
			in = new ObjectInputStream(new BufferedInputStream(sock.getInputStream()));
			while(true)
			{
				Packet message = (Packet) in.readObject();
				if(message.getOpcode().equals(6))
					break;
				TreatPacket(message);
				sleep(100);
			}
		}
		catch (SocketTimeoutException ste) 
		{
			Log.outTimed("Client " + sock.getInetAddress() + " timeout");
		}
		catch (Exception e) 
		{
			Log.outTimed("Client " + sock.getInetAddress() + " was disconnected...");
			try 
			{ sock.close(); } 
			catch (IOException e1) {}
			
		}
	}
	
	public void TreatPacket(Packet packt)
	{
		// TODO: handle p2p packets
	}
	
}
