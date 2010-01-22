package thread;

import java.io.IOException;
import java.net.Socket;

import socket.listener;

public class thr_listen extends Thread{

	private static Socket socket;
	private static listener listnr;
	public thr_listen(Socket sock)
	{
		socket = sock;
	}
	
	public void run()
	{
		try 
		{
			listnr = new listener(socket);
		} 
		catch (IOException e) 
		{
		
			e.printStackTrace();
		}
	}
	
	public static listener GetListener()
	{
		return listnr;
	}
	
}
