package thread;

import misc.Config;
import misc.Log;
import session.MirrorHandler;
import socket.ServerList;

/*
 * mirroring handler
 */
public class thr_mirrorhandler extends Thread {

	public thr_mirrorhandler(){}
	
	public void run()
	{
		/*
		 * init serverlist and mirror handler
		 */
		Log.outString("Starting Mirror Handler Thread");
		new ServerList();
		new MirrorHandler();
		
		try 
		{
			while(true)
			{
				// we must speak to upper mirrors
				MirrorHandler.Update();
				// 30*latency is sufficient
				Thread.sleep(30*Config.getLatency());
			}
		}
		catch (InterruptedException e) 
		{
			Log.outError("MirrorHandler Thread Error");
		}
	}
}
