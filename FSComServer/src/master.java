import misc.Config;
import misc.Log;

import thread.*;

/*
 * Master class
 * Launch all necessary threads
 */
public class master 
{
	private final static String version = "Release 1.2.5";
	
	public static void main(String args[])
	{
		Log.outString("FSS Com Server version " + version);
		Log.outString("Loading configuration file");
		/*
		 * Load config file
		 * Set the database defines
		 * Set Server ID
		 */
		Config.LoadConfig();
		Log.outString("Server ID : " + Config.getServerId());
		/*
		 * Start the socket for listen clients
		 */
		thr_listen lst = new thr_listen();
		lst.start();
		/*
		 * Start permanent connection with database 
		 */
		thr_database dbt = new thr_database();
		dbt.start();
		/*
		 * Start SessionHandler & others handlers
		 */
		thr_sesshandler tsh = new thr_sesshandler();
		tsh.start();
		/*
		 * Start MirrorHandler if it's not master mirror
		 */
		if(Config.getServerId() > 1)
		{
			thr_mirrorhandler thm = new thr_mirrorhandler();
			thm.start();
		}
		
	}
}
