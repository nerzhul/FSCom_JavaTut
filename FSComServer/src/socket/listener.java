package socket;

import java.io.*;
import java.net.*;

import socket.packet.packet_handler;

import misc.Log;

public class listener extends Thread{

	
	private Socket sockt;
	
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
	
	public listener(Socket sock)
	{
		this.sockt = sock;
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
	
	public void TreatPacket(String packt) throws IOException
	{
		// affichage du packet
		packet_handler packopt = new packet_handler(packt);
		packopt.ShowPacket();
		//Log.outString(packt);
		PrintStream out = new PrintStream(sockt.getOutputStream());
		out.println(packt);

	}
	
	
}
