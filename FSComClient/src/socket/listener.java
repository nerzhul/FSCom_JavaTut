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
			Log.outTimed("Client "  + sockt.getInetAddress() + " request connect to server");
			
			BufferedReader in = new BufferedReader(new InputStreamReader(sockt.getInputStream()));
			
			while(true)
			{
				String message = "";
				
				message = in.readLine();
				
				if(message.equals("close"))
					break;
				//sockt.setSoTimeout(20000);
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
	
	public void TreatPacket(Object packt) throws IOException
	{
		// show the packet
		packet_handler packopt = new packet_handler(packt,sockt);
		PrintStream out = new PrintStream(sockt.getOutputStream());
		out.println(packt);

	}
}
