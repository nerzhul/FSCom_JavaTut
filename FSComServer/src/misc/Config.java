package misc;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

import session.ServerList;

/*
 * This class contains handler for configuration file & vars
 * LogLevels : 
 * 0 : basic, no texts
 * 1: error
 * 2: debug
 * 3: full
 */
public class Config {

	
	private static String db_ip,db_name,db_user,db_pwd;
	private static Integer server_id,loglevel;

	public static void LoadConfig()
	{
		// conf file is this:
		String file = "CookieServer.conf";

		// trying to read config file
		try
		{
			// take file into buffer
			InputStream ips = new FileInputStream(file); 
			InputStreamReader ipsr = new InputStreamReader(ips);
			BufferedReader br = new BufferedReader(ipsr);
			String ligne;
			// we read line per line & register vars
			while ((ligne = br.readLine())!=null)
				RegisterVar(ligne);

			br.close();
		}		
		catch (Exception e)
		{
			// File error. We cannot read config process must be stopped
			Log.outError("Could not Open configuration file, aborted");
			System.exit(-1);
		}
	}
	
	public static void RegisterVar(String line)
	{
		// if there is'nt key & val we don't treat the line
		String KeyValTab[] = line.split("=");
		if(KeyValTab.length != 2)
			return;
		
		// else we store datas
		if(KeyValTab[0].equals("database_name"))
			db_name = KeyValTab[1];
		else if(KeyValTab[0].equals("database_user"))
			db_user = KeyValTab[1];
		else if(KeyValTab[0].equals("database_pwd"))
			db_pwd = KeyValTab[1];
		else if(KeyValTab[0].equals("database_address"))
			db_ip = KeyValTab[1];
		else if(KeyValTab[0].equals("log_level"))
			loglevel = Integer.decode(KeyValTab[1]);
		else if(KeyValTab[0].equals("server_id"))
		{
			server_id = Integer.decode(KeyValTab[1]);
			// if server_id is'nt registered we cannot continue.
			if(server_id > ServerList.getMaxMirrorList())
			{
				Log.outError("Bad Mirror Id");
				System.exit(-1);
			}
		}
	}
	
	public static String getDbName() { return db_name; }
	public static String getDbUser() { return db_user; }
	public static String getDbAddress() { return db_ip; }
	public static String getDbPwd() { return db_pwd; }
	public static Integer getServerId() { return server_id; }
	public static Integer getLoglevel() { return loglevel; }
}
