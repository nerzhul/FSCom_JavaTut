package socket;

import java.io.*;
import java.net.*;

import session.SessionHandler;
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
			sess = new session((Thread)this,sockt);
			in = new ObjectInputStream(new BufferedInputStream(sockt.getInputStream()));

			while(true)
			{
				packet message = (packet) in.readObject();
				
				if(message.getOpcode().equals(6))
					break;
				TreatPacket(message);
				sleep(100);
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
			{ sockt.close(); } 
			catch (IOException e1) {}
			
			SessionHandler.DestroySession(sess, this);
		}
	}
	
	public void TreatPacket(packet packt)
	{
		packopt = new packet_handler(packt,sockt, sess);
		packopt.Destroy();
	}
	
	
}
