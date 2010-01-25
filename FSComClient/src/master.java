import java.io.IOException;

import misc.Log;




import thread.*;
public class master
{

	private static thr_sender t_send;
	final static String version = "Alpha 0.3.2";
	
	public master()	{}
	
	public static void main(String args[]) throws IOException, InterruptedException
	{
		Log.outString("FSS Com Client version " + version);
		Log.outError("Test des logs erreur...");
		t_send = new thr_sender();
		t_send.start();
	}
	
	protected void finalize()
	{
		t_send.stopSock();
		t_send.interrupt();
	}
}
