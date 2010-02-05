import java.io.IOException;

import misc.Log;
import socket.serverlist;
import thread.*;

public class master
{

	private static threading thr_lib;
	final static String version = "Alpha 0.5.11b";
	
	public master()	{}
	
	public static void main(String args[]) throws IOException, InterruptedException
	{
		Log.outString("FSS Com Client version " + version);
		
		Log.outString("Initlialise mirror list...");
		new serverlist();
		Log.outString("Launch client...");
		thr_lib = new threading();
		thr_lib.start();
	}
	
	protected void finalize()
	{
		thr_lib.interrupt();
	}
}
