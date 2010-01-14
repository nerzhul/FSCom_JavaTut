import java.io.IOException;

import misc.Log;




import thread.*;
public class master
{

	final static String version = "Alpha 0.1.0";
	
	public master()
	{
		
	}
	public static void main(String args[]) throws IOException, InterruptedException
	{
		Log.outString("FSS Com Client version " + version);
		Log.outError("Test des logs erreur...");
		thr_sender t_send = new thr_sender();
		t_send.start();
	}
}
