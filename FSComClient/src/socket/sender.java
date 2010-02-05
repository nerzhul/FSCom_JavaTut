package socket;

import java.net.*;
import java.io.*;

import session.events;
import socket.packet.packet;
import thread.threading;
import windows.windowthread;

import misc.Log;
import misc.MasterCommandLine;

public class sender extends Thread
{
	private final static int port = 5677;
	private boolean connected = false;
	private static Socket socket;
	private static listener listn;
	private static ObjectOutputStream out;
	
	public sender()
	{
		Integer i = 0;
		
		while(i < serverlist.getMaxMirrorList() && !connected)
		{
			try 
			{
				Log.outError("Tentative de connexion au miroir " + i);
				socket = new Socket(serverlist.GetMirror(i),port);
				out = new ObjectOutputStream(new BufferedOutputStream(socket.getOutputStream()));
				connected = true;
			}
			catch (UnknownHostException e) 
			{
				e.printStackTrace();
			}
			catch(ConnectException e)
			{
				i++;	
			}
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		}
		if(!connected)
			events.ConnectionError();
	}
	
	public void run()
	{
		if(!connected)
		{
			this.interrupt();
			return;
		}
		
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
		    StopListener();
		    socket.close();
		    cmdline.Destroy();

		} 
		catch (Exception e) 
		{
	      this.interrupt();
	      e.printStackTrace();
	    }
	}
	
	public static void SendPacket(Integer opcode,Object packt)
	{
		if(out == null)
			return;
		
		try 
		{
			// send packet
			packet pck = new packet(opcode,packt);
			out.writeObject(pck);
			out.flush();
	    	Log.outTimed("Send packet (opcode :" + pck.getOpcode() + ") to server ");
		}
		catch(SocketException e)
		{
			windowthread.SwitchPanel(1);
			threading.StopSender();
			// TODO: handle correctly new connection (mirror dev)
		}
		catch (IOException e) 
		{
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

	public static void StopListener() 
	{
		if(listn != null)
			listn.interrupt();		
	}
}
