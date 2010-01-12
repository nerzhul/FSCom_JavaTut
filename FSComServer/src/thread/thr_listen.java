package thread;

import socket.listener;
import java.net.*;
import misc.Log;

public class thr_listen extends Thread
{
	final static int port = 22;
	
	public void run()
	{
		StartMasterListenThread();
	}
	
	public void StartMasterListenThread()
	{
		try
		{
			ServerSocket MasterListener = new ServerSocket(port);
			Log.outTimed("Starting master listener thread...");
			while (true) 
			{
		        Socket socketClient = MasterListener.accept();
		        listener listen_t = new listener(socketClient);
		        listen_t.start();
		     }
		}
		catch (Exception e) 
		{
		      e.printStackTrace();
		}

	}
}
