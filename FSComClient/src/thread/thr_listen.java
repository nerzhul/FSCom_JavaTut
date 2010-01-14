package thread;

import java.net.ServerSocket;
import java.net.Socket;

import misc.Log;
import socket.listener;

public class thr_listen extends Thread{

	final static int port = 5678;
	
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
		        listener listen_t = new listener(socketClient);
		        listen_t.start();
		        Log.outString("Master listener thread started succesfuly");
		     }
		}
		catch (Exception e) 
		{
		      e.printStackTrace();
		}

	}
}
