package socket;

import java.net.*;
import java.io.*;

import session.events;
import socket.packet.Packet;
import thread.threading;
import thread.windowthread;

import misc.Log;
import misc.MasterCommandLine;

public class Sender extends Thread
{
	private final static int port = 5677;
	private static boolean connected = false;
	private static Socket socket;
	private static Listener listn;
	private static ObjectOutputStream out;
	
	public Sender()
	{
		Connect();
	}
	
	public static void Connect()
	{
		Integer mir = ServerList.getBestMirror();
		if(mir != -1)
		{
			try 
			{
				socket = new Socket(ServerList.GetMirror(mir),port);
				out = new ObjectOutputStream(new BufferedOutputStream(socket.getOutputStream()));
				connected = true;
			}
			catch (Exception e) 
			{
				events.ConnectionError();
			}
		}
		else
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
		    
		    listn = new Listener(socket);
		    listn.start();
		    
		    threading.Launchp2pListener();
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
		    StopSocket();
		    cmdline.Destroy();

		} 
		catch (Exception e) 
		{
			StopListener();
		    StopSocket();
		    threading.Stopp2pListener();
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
			Packet pck = new Packet(opcode,packt);
			out.writeObject(pck);
			out.flush();
	    	Log.outTimed("Send packet (opcode :" + pck.getOpcode() + ") to server ");
		}
		catch(SocketException e)
		{
			windowthread.SwitchPanel(1);
			threading.StopSender();
			Log.ShowPopup("Vous avez �t� d�connect� du serveur. (m_" + ServerList.getCurrentMirror()+ ")",true);
			Connect();
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
