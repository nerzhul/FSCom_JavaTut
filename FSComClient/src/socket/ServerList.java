package socket;

import java.net.ConnectException;
import java.net.Socket;

import thread.thr_popup;

public final class ServerList {

	private static String[] iplist;
	private static Integer curr_mirror;
	private static Integer MAX_MIRROR = 4;
	private static thr_popup pop;
	//liste des serveurs
	public ServerList()
	{
		curr_mirror = 0;
		iplist = new String[MAX_MIRROR];
		iplist[2] = "172.20.9.203";
		iplist[3] = "www.blackdiamondserver.com";
		iplist[1] = "172.20.9.56";
		iplist[0] = "127.0.0.1";	
	}
	
	//fonction pour accéder aux mirroirs
	public static String GetMirror(Integer i)
	{
		if(i < MAX_MIRROR) 
			return iplist[i];
		else 
			return iplist[0];
	}

	//fonction pour avoir le nombre de mirroirs
	public static Integer getMaxMirrorList()
	{
		return MAX_MIRROR;
	}

	//fonction pour avoir le mirroir utilisé
	public static Integer getCurrentMirror()
	{
		return curr_mirror;
	}

	//fonction pour avoir le meilleur mirroir
	public static Integer getBestMirror()
	{
		Integer i=0;
		boolean found=false;
		//popup de recherche du mirroir
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
			catch (ConnectException e)
			{
				i++;
			}
			catch (Exception e) 
			{
				e.printStackTrace();
				i++;
			}
			
		}//on essaye de se connecter sur tout les mirroirs
		while(i<MAX_MIRROR && !found);
		pop.interrupt();
		
		return i;
	}
	
	//on ferme le popup une fois connecté
	public static void ClosePopup(){
		if(pop!=null)
			pop.interrupt();
	}
}
