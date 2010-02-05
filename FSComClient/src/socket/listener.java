package socket;

import java.io.BufferedInputStream;
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
	ObjectInputStream in;
	
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
			while(true)
			{
				try
				{
					in = new ObjectInputStream(new BufferedInputStream(sockt.getInputStream()));
				}
				catch(IOException e)
				{
					in = new ObjectInputStream(new BufferedInputStream(sockt.getInputStream()));
				}
				packet message = (packet) in.readObject();
				Log.outString("Packet received from server (opcode :" + message.getOpcode() + ")");
				
				if(message.getOpcode().equals(0x07))
					break;
				
				TreatPacket(message);
				sleep(100);
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
			e.printStackTrace();
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
