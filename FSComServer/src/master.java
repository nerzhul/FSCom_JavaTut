import java.io.IOException;

import thread.*;

public class master 
{
	final static String version = "Alpha 0.0.7";
	
	public static void main(String args[]) throws IOException
	{
		misc.Log.outString("FSS Com Server version " + version);
		misc.Log.outError("Test des logs erreur...");
		thr_listen lst = new thr_listen();
		lst.start();
	}

}

