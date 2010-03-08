package session;

public final class ServerList {

	private static String[] iplist;
	private static Integer MAX_MIRROR = 4;
	public ServerList()
	{
		iplist = new String[MAX_MIRROR];
		iplist[1] = "172.20.9.203";
		iplist[3] = "www.blackdiamondserver.com";
		iplist[0] = "172.20.9.56";
		iplist[2] = "127.0.0.1";	
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
}
