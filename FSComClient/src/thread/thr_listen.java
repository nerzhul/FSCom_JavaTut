package thread;

import java.io.IOException;
import java.net.Socket;

import socket.Listener;

public class thr_listen extends Thread{

	private static Socket socket;
	private static Listener listnr;
	public thr_listen(Socket sock)
	{
		socket = sock;
	}
	
	public void run()
	{
		try 
		{
			listnr = new Listener(socket);
		} 
		catch (IOException e) 
		{
		
			e.printStackTrace();
		}
	}
	
	public static Listener GetListener()
	{
		return listnr;
	}
	
}
