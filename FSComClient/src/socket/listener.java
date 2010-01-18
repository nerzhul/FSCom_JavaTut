package socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.SocketTimeoutException;

import misc.Log;
import socket.packet.packet_handler;

public class listener extends Thread{
	
	Socket sockt;
	
	
	public listener(Socket sock)
	{
		this.sockt = sock;
	}

	public void run()
	{
		try 
		{
			ListenAndDo();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	public void ListenAndDo() throws IOException
	{
		try
		{
			Log.outTimed("Server request connect to client");
			
			BufferedReader in = new BufferedReader(new InputStreamReader(sockt.getInputStream()));
			
			while(true)
			{
				String message = "";
				
				message = in.readLine();
				
				if(message.equals("0x070"))
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
	      
			Log.outError("Listening Server Socket Error !");
			e.printStackTrace();
		}
	}
	
	public void TreatPacket(Object packt) throws IOException
	{
		// show the packet
		packet_handler packopt = new packet_handler(packt,sockt);
		PrintStream out = new PrintStream(sockt.getOutputStream());
		out.println(packt);
	}
}
