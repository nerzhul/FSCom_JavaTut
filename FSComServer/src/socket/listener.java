package socket;

import java.io.*;
import java.net.*;

import misc.Log;

public class listener extends Thread{

	
	private Socket sockt;
	
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
			String message = "";
	
			System.out.println("Client "  + sockt.getInetAddress() + " request connect to server");

			BufferedReader in = new BufferedReader(new InputStreamReader(sockt.getInputStream()));
			message = in.readLine();

			//TreatPacket(message);

			sockt.close();
	    } 
		catch (Exception e) 
		{
	      e.printStackTrace();
		}
	}
	
	public void TreatPacket(String packt) throws IOException
	{
		// affichage du packet
		Log.outString(packt);
	}
	
	
}
