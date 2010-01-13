import java.io.IOException;

import thread.*;
import misc.Log;

public class master 
{
	final static String version = "Alpha 0.1.0";
	
	public master()
	{
		
	}
	
	public static void main(String args[]) throws IOException
	{
		Log.outString("FSS Com Server version " + version);
		Log.outError("Test des logs erreur...");
		thr_listen lst = new thr_listen();
		lst.start();
	}

}

