package socket;

import java.io.BufferedInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;

import misc.Log;
import socket.packet.Packet;
import socket.packet.Packet_handler;
/*
 * thread d'écoute
 */
public class Listener extends Thread
{
	Socket sockt;
	ObjectInputStream in;
	
	public Listener(Socket sock) throws IOException
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
			while(true)//boucle d'écoute
			{

				in = new ObjectInputStream(new BufferedInputStream(sockt.getInputStream()));
				
				Packet message = (Packet) in.readObject();
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
		catch(EOFException e)
		{
			try {
				sockt.close();
			} catch (IOException e1) {
				this.interrupt();
			}
			this.interrupt();
		}
		catch (SocketException e)
		{
			this.interrupt();
		}
		catch(InterruptedException e)
		{
			// nothing to do client was already disconnected
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			this.interrupt();
		}
	}
	
	public void TreatPacket(Packet packt) throws IOException
	{
		Packet_handler packopt = new Packet_handler(packt,sockt);
		packopt.Destroy();
	}
}
