package session;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import misc.Config;

public class MirrorHandler {

	private static Integer myId;
	public MirrorHandler()
	{
		myId = Config.getServerId();
	}
	
	public static void Update()
	{
		if(myId.equals(1))
			return;
		
		for(int i=0;i<myId;i++)
		{
			try 
			{
				Socket sock = new Socket(ServerList.GetMirror(i),5677);
				SessionHandler.DisconnectAllClients();
				sock.close();
				return;
			}
			catch (UnknownHostException e) 
			{
				continue;
			} 
			catch (IOException e) 
			{
				continue;
			}
			
		}
	}
}
