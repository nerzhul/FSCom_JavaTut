package socket;

import java.net.*;
import java.io.*;

import session.events;
import socket.packet.packet;

import misc.Log;
import misc.MasterCommandLine;

public class sender extends Thread
{
	final static int port = 5677;
	final static String IP = "127.0.0.1";

	private static Socket socket;
	private static listener listn;
	private static ObjectOutputStream out;
	
	public sender()
	{
		try 
		{
			socket = new Socket(IP,port);
			out = new ObjectOutputStream(new BufferedOutputStream(socket.getOutputStream()));
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
	    
		    Log.outString("Close connection with server !");
		    socket.close();
		    cmdline.Destroy();

		} 
		catch (Exception e) 
		{
	      events.ConnectionError();
	      this.interrupt();
	    }
	}
	
	public static void SendPacket(Integer opcode,Object packt)
	{
		// creating buffers for packets to send
		
		try 
		{
			// send packet
			packet pck = new packet(opcode,packt);
			out.writeObject(pck);
			out.flush();
	    	Log.outTimed("Send packet (opcode :" + pck.getOpcode() + ") to server ");
		} catch (IOException e) {
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
