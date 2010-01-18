import java.io.IOException;

import misc.Log;




import thread.*;
public class master
{

	private static thr_sender t_send;
	final static String version = "Alpha 0.1.1";
	
	public master()
	{
		
	}
	public static void main(String args[]) throws IOException, InterruptedException
	{
		Log.outString("FSS Com Client version " + version);
		Log.outError("Test des logs erreur...");
		t_send = new thr_sender();
		t_send.start();
	}
	
	protected void finalize()
	{
		try 
		{
			t_send.stopSock();
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		t_send.interrupt();
	}

}
