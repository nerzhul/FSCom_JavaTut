package socket;

import java.io.*;
import java.net.*;

import session.SessionHandler;
import session.session;
import socket.packet.Packet;
import socket.packet.Packet_handler;

import misc.Config;
import misc.Log;

/*
 * Listen thread for one client
 */
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
		// famous try catch for network prog
		try
		{
			Log.outTimed("Client "  + sockt.getInetAddress() + " request connect to server");
			// new client connected => session
			sess = new session((Thread)this,sockt);
			// buffer the listening stream
			in = new ObjectInputStream(new BufferedInputStream(sockt.getInputStream()));

			while(true)
			{
				// read the stream
				Packet message = (Packet) in.readObject();
				
				// if opcode is 6 disconnect force
				if(message.getOpcode().equals(6))
					break;
				
				// get packet to do sth
				TreatPacket(message);
				
				// w8 for chip improvements
				sleep(Config.getLatency());
			}
			
			Log.outTimed("Close connection with " + sockt.getInetAddress());
			// close socket properly
			sockt.close();
	    } 
		catch (SocketTimeoutException ste) 
		{
			try 
			{ sockt.close(); } 
			catch (IOException e1) {}
			Log.outTimed("Client " + sockt.getInetAddress() + " timeout");
		}
		catch (InterruptedException e) 
		{
			Log.outError("Threading Error !");
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
			Log.outError("Client " + sockt.getInetAddress() + " send invalid packet");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void TreatPacket(Packet packt)
	{
		// create packethandler for this packet 
		packopt = new Packet_handler(packt,sockt, sess);
		packopt.Destroy();
	}
}
