import misc.Config;
import misc.Log;

import thread.*;
public class master 
{
	final static String version = "Release 1.2.1";
	
	public static void main(String args[])
	{
		Log.outString("FSS Com Server version " + version);
		Log.outString("Loading configuration file");
		Config.LoadConfig();
		Log.outString("Server ID : " + Config.getServerId());
		thr_listen lst = new thr_listen();
		lst.start();
		thr_database dbt = new thr_database();
		dbt.start();
		thr_sesshandler tsh = new thr_sesshandler();
		tsh.start();
		if(Config.getServerId() > 1)
		{
			thr_mirrorhandler thm = new thr_mirrorhandler();
			thm.start();
		}
		
	}
}
