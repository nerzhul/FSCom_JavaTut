package thread;
import java.io.IOException;

import socket.sender;
public class thr_sender extends Thread{

	public sender thsend;
	public thr_sender()
	{
		
	}
	
	public void run()
	{
		thsend = new sender();
		thsend.start();
	}
	
	public void stopSock() throws IOException
	{
		thsend.StopSocket();
	}
}
