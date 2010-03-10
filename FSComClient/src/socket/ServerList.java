package socket;

import java.net.Socket;

import thread.thr_popup;

public final class ServerList {

	private static String[] iplist;
	private static Integer curr_mirror;
	private static Integer MAX_MIRROR = 4;
	private static thr_popup pop;
	public ServerList()
	{
		curr_mirror = 0;
		iplist = new String[MAX_MIRROR];
		iplist[2] = "172.20.9.203";
		iplist[3] = "www.blackdiamondserver.com";
		iplist[1] = "172.20.9.56";
		iplist[0] = "127.0.0.1";	
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
		pop = new thr_popup("Recherche du meilleur miroir...","Connexion en cours....", false);
		pop.start();
		do
		{
			try
			{
				Socket sock = new Socket(iplist[i],5677);
				sock.close();
				pop.interrupt();
				return i;
				
			}
			catch (Exception e) 
			{
				e.printStackTrace();
				i++;
			}
		}
		while(i<MAX_MIRROR && !found);
		pop.interrupt();
		
		return i;
	}
	
	public static void ClosePopup(){
		if(pop!=null)
			pop.interrupt();
	}
}
