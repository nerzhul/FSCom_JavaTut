package socket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.SocketTimeoutException;

import misc.Log;
import socket.packet.packet;
import socket.packet.packet_handler;

public class listener extends Thread
{
	
	Socket sockt;
	
	public listener(Socket sock) throws IOException
	{
		this.sockt = sock;
	}
	
	public void run()
	{
		ListenAndDo();
	}
	
	public void ListenAndDo()
	{
		try
		{
			ObjectInputStream in = new ObjectInputStream(sockt.getInputStream());
			
			while(true)
			{
				packet message = (packet) in.readObject();
				
				if(message.getOpcode().equals(7))
					break;
				
				TreatPacket(message);
			}
			Log.outTimed("Close connection with server");
			sockt.close();
	    } 
		catch (SocketTimeoutException ste) 
		{
			Log.outTimed("Server connection timeout");
		}
		catch (Exception e) 
		{
			this.interrupt();
		}
	}
	
	public void TreatPacket(packet packt) throws IOException
	{
		// show the packet
		packet_handler packopt = new packet_handler(packt,sockt);
		packopt.Destroy();
	}
}
