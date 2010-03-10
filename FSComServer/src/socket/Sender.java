package socket;

import java.net.*;
import java.io.*;

import session.session;
import socket.packet.Packet;

import misc.Log;

/*
 * used to send packet to client 
 */
public class Sender
{
	private Socket socket;
	private Packet pck;
	private session sess;
	public Sender(Socket sock,Integer op,Object packet,session _sess)
	{
		socket = sock;
		pck = new Packet(op,packet);
		sess = _sess;
	}

	public void CloseConnection()
	{
		// disconnect properly
		try 
		{
			socket.close();
		} 
		catch (IOException e)
		{
			Log.outError("Socket with " + socket.getInetAddress() + " already closed");
		}
		if(sess != null)
			sess.disconnect_client();
	}
	
	public void SendPacket()
	{
		// out stream
		ObjectOutputStream out;
		
		try 
		{
			out = new ObjectOutputStream(new BufferedOutputStream(socket.getOutputStream()));
			// send packet
			out.writeObject(pck);
			// clean buffer
			out.flush();
	    	Log.outTimed("Send packet " + pck.getOpcode() + ": " + pck.getData() + " to client : " + socket.getInetAddress());
		}
		catch(SocketException e)
		{
			CloseConnection();
		}
		catch (IOException e) 
		{
			Log.outError("Socket Write error, closing connection with " + socket.getInetAddress());
			CloseConnection();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
