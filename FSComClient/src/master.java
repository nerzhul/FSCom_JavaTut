import java.io.IOException;

import misc.Log;
import misc.Misc;
import socket.ServerList;
import thread.*;
/*
 * 
 */
public class master
{
	private static threading thr_lib;
	private final static String version = "Release 1.0.1";//version du client
	
	public master()	{}
	
	public static void main(String args[]) throws IOException, InterruptedException
	{
		Log.outString("FSS Com Client version " + version);
		Misc.RegisterVersion(version);
		new ServerList();//recupération de la liste des serveurs
		thr_lib = new threading();
		thr_lib.start();//lancement d'un thread
	}
	
	protected void finalize()
	{
		thr_lib.interrupt();//intéruption du thread
	}
	
}
