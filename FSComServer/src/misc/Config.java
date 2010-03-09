package misc;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

import session.ServerList;

public class Config {

	
	private static String db_ip,db_name,db_user,db_pwd;
	private static Integer server_id;

	public static void LoadConfig()
	{
		String file = "CookieServer.conf";

		try
		{
			InputStream ips = new FileInputStream(file); 
			InputStreamReader ipsr = new InputStreamReader(ips);
			BufferedReader br = new BufferedReader(ipsr);
			String ligne;
			while ((ligne = br.readLine())!=null)
				RegisterVar(ligne);

			br.close();
		}		
		catch (Exception e)
		{
			Log.outError("Could not Open configuration file, aborted");
			System.exit(-1);
		}
	}
	
	public static void RegisterVar(String line)
	{
		String KeyValTab[] = line.split("=");
		if(KeyValTab.length != 2)
			return;
		
		if(KeyValTab[0].equals("database_name"))
			db_name = KeyValTab[1];
		else if(KeyValTab[0].equals("database_user"))
			db_user = KeyValTab[1];
		else if(KeyValTab[0].equals("database_pwd"))
			db_pwd = KeyValTab[1];
		else if(KeyValTab[0].equals("database_address"))
			db_ip = KeyValTab[1];
		else if(KeyValTab[0].equals("server_id"))
		{
			server_id = Integer.decode(KeyValTab[1]);
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
}
