package socket;

import java.net.*;
import java.io.*;

import misc.Log;
import misc.MasterCommandLine;

public class sender extends Thread
{
	final static int port = 5677;
	final static String IP = "127.0.0.1";

	private static Socket socket;
	private static listener listn;
	
	public sender()
	{
		try 
		{
			socket = new Socket(IP,port);
		}
		catch (UnknownHostException e) 
		{
			e.printStackTrace();
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	public void run()
	{
		try
		{
			// create a socket on mine IP.
		    Log.outString("Initialising Master Command Line...");
		    MasterCommandLine cmdline = new MasterCommandLine();
		    Log.outString("Master Command Line Initialized");
		    
		    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		    String line;
		    
		    listn = new listener(socket);
		    listn.start();
		    
		    MasterCommandLine.DoCommand("ping");
		    while (true) 
		    {
		    	Log.outPrompt();
		    	line = reader.readLine();
		    	if(line.equals("close"))
		    		break;
		    	MasterCommandLine.DoCommand(line);
		    }
		    // don't forget to close the socket or client get an internal error !
		    
		    Log.outString("Close connection with server !");
		    socket.close();
		    cmdline.Destroy();

		} 
		catch (Exception e) 
		{
	      Log.ShowPopup("Echec de connexion au serveur !", true);
	      this.interrupt();
	    }
	}
	
	public static void SendPacket(Integer opcode,Object packt)
	{
		// creating buffers for packets to send
		PrintWriter out;
		try {
			out = new PrintWriter(socket.getOutputStream(), true);
			// send packet
			String str = "0x";
			if(opcode < 16)
				str += "0";
			str += Integer.toHexString(opcode);		
			str += packt;
	    	out.println(str);
	    	Log.outTimed("Send packet : " + str + " to server ");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void StopSocket()
	{
	     try
	     {
	    	 Log.outString("Close connection with server");
	    	 socket.close();
	     } 
	     catch (IOException e) 
	     {
			 Log.outError("Could not close socket");
			 System.exit(-1);
	     }
	}
	
	public static Socket getSocket()
	{
		return socket;
	}

	public void StopListener() 
	{
		if(listn != null)
			listn.interrupt();		
	}
}
