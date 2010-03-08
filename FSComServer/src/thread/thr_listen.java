package thread;

import socket.Listener;
import java.net.*;
import misc.Log;

public class thr_listen extends Thread
{
	final static int port = 5677;
	
	public void run()
	{
		StartMasterListenThread();
	}
	
	public void StartMasterListenThread()
	{
		try
		{
			ServerSocket MasterListener = new ServerSocket(port);
			Log.outString("Starting master listener thread...");
			while (true) 
			{
		        Socket socketClient = MasterListener.accept();
		        Listener listen_t = new Listener(socketClient);
		        listen_t.start();
		     }
		}
		catch (Exception e) 
		{
			Log.outError("Threading error on Master Listener, process aborted");
		    e.printStackTrace();
		    System.exit(-1);
		}
	}
}
