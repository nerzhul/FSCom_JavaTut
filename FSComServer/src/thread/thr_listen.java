package thread;

import socket.Listener;
import java.net.*;

import misc.Config;
import misc.Log;

/*
 * listening thread
 */
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
			/*
			 * create a listening thread on server port
			 */
			ServerSocket MasterListener = new ServerSocket(port);
			Log.outString("Starting master listener thread...");
			while (true) 
			{
				/*
				 * when client connects we accept him and create a thread for him
				 */
		        Socket socketClient = MasterListener.accept();
		        Listener listen_t = new Listener(socketClient);
		        listen_t.start();
		        // improve core loops is important
		        sleep(Config.getLatency());
		    }
		}
		catch (Exception e) 
		{
			/*
			 * we can't listen ? no reason to live
			 */
			Log.outError("Threading error on Master Listener, process aborted");
		    e.printStackTrace();
		    System.exit(-1);
		}
	}
}
