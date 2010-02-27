package thread;

import java.net.ServerSocket;
import java.net.Socket;

import misc.Log;
import socket.p2pListener;

public class thr_listen_p2p extends Thread {

	final static int port = 5678;
	
	public void run()
	{
		Startp2pListener();
	}
	
	private void Startp2pListener()
	{
		try
		{
			ServerSocket p2pListener = new ServerSocket(port);
			Log.outString("Starting p2p listener thread...");
			while (true) 
			{
		        Socket socketClient = p2pListener.accept();
		        p2pListener listen_t = new p2pListener(socketClient);
		        listen_t.start();
		        Log.outString("Master p2p thread started succesfully");
		     }
		}
		catch (Exception e) 
		{
		      e.printStackTrace();
		}
	}
}
