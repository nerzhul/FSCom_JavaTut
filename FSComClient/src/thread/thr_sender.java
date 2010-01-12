package thread;
import socket.sender;
public class thr_sender extends Thread{

	public thr_sender()
	{
		
	}
	
	public void run()
	{
		sender thsend = new sender();
		thsend.start();
	}
}
