package socket;

import java.io.*;
import java.net.*;

public class listener extends Thread{

	
	private Socket sockt;
	
	public void run()
	{
		ListenAndDo();
	}
	
	public listener(Socket sock)
	{
		this.sockt = sock;
	}
	
	public void ListenAndDo()
	{
		
	}
	
	
}
