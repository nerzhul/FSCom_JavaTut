package socket;

import java.io.*;
import java.net.*;

import session.session;
import socket.packet.packet;
import socket.packet.packet_handler;

import misc.Log;

public class listener extends Thread
{
	private Socket sockt;
	private session sess;
	packet_handler packopt;
	ObjectInputStream in;
	
	public void run()
	{
		ListenAndDo();
	}

	public listener(Socket sock)
	{
		this.sockt = sock;
	}
	
	public void ListenAndDo()
	{
		try
		{
			Log.outTimed("Client "  + sockt.getInetAddress() + " request connect to server");
			in = new ObjectInputStream(sockt.getInputStream());
			sess = new session((Thread)this,sockt);

			while(true)
			{
				packet message = (packet) in.readObject();
				
				if(message.getOpcode().equals(6))
					break;
				TreatPacket(message);
			}
			
			Log.outTimed("Close connection with " + sockt.getInetAddress());
			sockt.close();
	    } 
		catch (SocketTimeoutException ste) 
		{
			Log.outTimed("Client " + sockt.getInetAddress() + " timeout");
		}
		catch (Exception e) 
		{
			Log.outTimed("Client " + sockt.getInetAddress() + " was disconnected...");
			try 
			{	sockt.close();	} 
			catch (IOException e1) {}
			this.interrupt();
		}
	}
	
	public void TreatPacket(packet packt)
	{
		packopt = new packet_handler(packt,sockt, sess);
		packopt.Destroy();
	}
	
	
}
