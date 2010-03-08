package thread;

import misc.Log;
import session.MirrorHandler;
import session.ServerList;

public class thr_mirrorhandler extends Thread {

	public thr_mirrorhandler(){}
	
	public void run()
	{
		Log.outString("Starting Mirror Handler Thread");
		new ServerList();
		new MirrorHandler();
		
		try 
		{
			while(true)
			{
				MirrorHandler.Update();
				Thread.sleep(30000);
			}
		}
		catch (InterruptedException e) 
		{
			Log.outError("MirrorHandler Thread Error");
		}
	}
}
