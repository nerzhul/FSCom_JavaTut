package socket;

public final class serverlist {

	private static String[] iplist;
	private static Integer MAX_MIRROR = 4;
	public serverlist()
	{
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
}
