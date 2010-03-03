package socket;

import java.io.*;
import java.net.*;

import session.SessionHandler;
import session.session;
import socket.packet.Packet;
import socket.packet.Packet_handler;

import misc.Log;

public class Listener extends Thread
{
	private Socket sockt;
	private session sess;
	Packet_handler packopt;
	ObjectInputStream in;
	
	public void run()
	{
		ListenAndDo();
	}

	public Listener(Socket sock)
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
				Packet message = (Packet) in.readObject();
				
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
		catch (InterruptedException e) 
		{
			Log.outError("Listener Thread Error !");
		} 
		catch (IOException e) 
		{
			Log.outTimed("Client " + sockt.getInetAddress() + " was disconnected...");
			try 
			{ sockt.close(); } 
			catch (IOException e1) {}
			
			SessionHandler.DestroySession(sess, this);
		} 
		catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void TreatPacket(Packet packt)
	{
		packopt = new Packet_handler(packt,sockt, sess);
		packopt.Destroy();
	}
	
	
}
