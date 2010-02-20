package socket;

import java.net.Socket;

import misc.Log;

public final class serverlist {

	private static String[] iplist;
	private static Integer curr_mirror;
	private static Integer MAX_MIRROR = 4;
	public serverlist()
	{
		curr_mirror = 0;
		iplist = new String[MAX_MIRROR];
		iplist[0] = "127.0.0.1";
		iplist[1] = "127.0.0.1";
		iplist[2] = "127.0.0.1";
		iplist[3] = "127.0.0.1";	
	}
	
	public static String GetMirror(Integer i)
	{
		if(i < MAX_MIRROR) 
			return iplist[i];
		else 
			return iplist[0];
	}

	public static Integer getMaxMirrorList()
	{
		return MAX_MIRROR;
	}

	public static Integer getCurrentMirror()
	{
		return curr_mirror;
	}

	public static Integer getBestMirror()
	{
		Integer i=0;
		boolean found=false;
		Log.ShowPopup("Recherche du meilleur miroir...", false);
		do
		{
			try
			{
				Socket sock = new Socket(iplist[i],5677);
				sock.close();
				return i;
			}
			catch (Exception e) 
			{
				i++;
			}
		}
		while(i<MAX_MIRROR && !found);
		return -1;
	}
}
