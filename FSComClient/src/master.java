import java.io.IOException;

import misc.*;
import thread.*;
public class master
{

	final static String version = "Alpha 0.0.3";
	
	public static void main(String args[]) throws IOException
	{
		Log.outString("FSS Com Client version " + version);
		Log.outError("Test des logs erreur...");
		thr_sender t_send = new thr_sender();
		t_send.start();
		
	}
}
