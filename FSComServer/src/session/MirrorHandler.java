package session;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import socket.ServerList;

import misc.Config;

/*
 * Mirror handler for prevent machine failure
 */
public class MirrorHandler {

	// store id
	private final static Integer myId = Config.getServerId();
	
	public static void Update()
	{
		// if it's master mirror, don't speak with other servers
		if(myId.equals(1))
			return;
		
		for(int i=0;i<myId;i++)
		{
			// trying to connect to upper mirror
			try 
			{
				Socket sock = new Socket(ServerList.GetMirror(i),5677);
				// success, we disconnect all clients
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
