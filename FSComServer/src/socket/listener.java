package socket;

import java.io.*;
import java.net.*;

import session.session;
import socket.packet.packet_handler;

import misc.Log;

public class listener extends Thread{

	
	private Socket sockt;
	private session sess;
	packet_handler packopt;
	
	
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
			BufferedReader in = new BufferedReader(new InputStreamReader(sockt.getInputStream()));
			sess = new session((Thread)this,sockt);
			
			while(true)
			{
				String message = "";
				
				message = in.readLine();
				
				if(message.equals("0x060"))
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
	      
			Log.outError("Listening Master Socket Error !");
			e.printStackTrace();
		}
	}
	
	public void TreatPacket(Object packt)
	{
		packet_handler packopt = new packet_handler(packt,sockt, sess);
		packopt.Destroy();
	}
	
	
}
