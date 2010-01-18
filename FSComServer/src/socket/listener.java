package socket;

import java.io.*;
import java.net.*;

import socket.packet.packet_handler;
import thread.thr_sender;

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

			Log.outString("Starting Response Thread with client");
			thr_sender response = new thr_sender(sockt.getInetAddress().toString());
			response.start();
			Log.outString("Reponse thread started succesfully.");
			while(true)
			{
				String message = "";
				
				message = in.readLine();
				
				if(message.equals("0x060"))
					break;
				TreatPacket(message);
			}
			
			Log.outTimed("Close connection with " + sockt.getInetAddress());
			response.CloseConnection();
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
