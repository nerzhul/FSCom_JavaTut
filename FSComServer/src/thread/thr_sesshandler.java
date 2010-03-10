package thread;

import session.AvatarHandler;
import session.SessionHandler;
import misc.Config;
import misc.Log;

/*
 * unused sessionhandler, must be improved to close threads for disconnected
 * client by network cause
 */
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
				/*
				 * must send ping to all clients and wait their answer
				 */
				SessionHandler.Update(uDiff);
				sleep(uDiff);
			} 
			catch (InterruptedException e) 
			{
				// sessionhandler could'nt verify clients ? bye bye.
				System.exit(-1);
			}
			
		}
	}
}
