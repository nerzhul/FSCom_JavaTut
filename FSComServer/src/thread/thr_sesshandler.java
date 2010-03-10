package thread;

import session.AvatarHandler;
import session.SessionHandler;
import misc.Config;
import misc.Log;

public class thr_sesshandler extends Thread{

	private static int uDiff = 5*Config.getLatency();;
	public void run()
	{
		Log.outString("Starting Session handler thread...");
		new SessionHandler();
		new AvatarHandler();
		while(true)
		{
			try 
			{
				
				SessionHandler.Update(uDiff);
				sleep(uDiff);
			} 
			catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
			
		}
	}
}
