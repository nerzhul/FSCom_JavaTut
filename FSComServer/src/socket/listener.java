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
	      e.printStackTrace();
		}
	}
	
	public void TreatPacket(String packt) throws IOException
	{
		// affichage du packet
		Log.outString(packt);
		PrintStream out = new PrintStream(sockt.getOutputStream());
		out.println(packt);

	}
	
	
}
