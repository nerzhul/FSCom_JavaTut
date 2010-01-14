package thread;
import java.io.IOException;

import socket.sender;
public class thr_sender extends Thread{

	private String IP;
	sender thsend;
	public thr_sender(String ip)
	{
		IP = ip;
	}
	
	public void run()
	{
		thsend = new sender(IP);
		thsend.start();
	}
	
	public void CloseConnection() throws IOException
	{
		thsend.CloseConnection();
	}
}
