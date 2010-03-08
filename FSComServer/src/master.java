import misc.Config;
import misc.Log;

import thread.*;
public class master 
{
	final static String version = "Release 1.1.4";
	
	public static void main(String args[])
	{
		Log.outString("FSS Com Server version " + version);
		Log.outString("Loading configuration file");
		Config.LoadConfig();
		thr_listen lst = new thr_listen();
		lst.start();
		thr_database dbt = new thr_database();
		dbt.start();
		thr_sesshandler tsh = new thr_sesshandler();
		tsh.start();
		
	}
}
