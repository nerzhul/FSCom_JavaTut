import java.io.IOException;
import java.sql.SQLException;

import misc.Log;

import thread.*;

public class master 
{
	final static String version = "Alpha 0.1.3";
	
	public static void main(String args[]) throws IOException, SQLException
	{
		Log.outString("FSS Com Server version " + version);
		Log.outError("Test des logs erreur...");
		thr_listen lst = new thr_listen();
		lst.start();
		thr_database dbt = new thr_database();
		dbt.start();
	}

}

