package thread;

import session.SessionHandler;
import misc.Log;

public class thr_sesshandler extends Thread{

	private static int uDiff;
	public void run()
	{
		Log.outString("Starting Session handler thread...");
		new SessionHandler();
		while(true)
		{
			
			try 
			{
				sleep(1000);
				uDiff = 1000;
				SessionHandler.Update(uDiff);
			} catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
			
		}
	}
}