import java.io.IOException;
import thread.thr_sender;

public class master {

	final static String version = "Alpha 0.0.5";
	
	public static void main(String args[]) throws IOException
	{
		misc.Log.outString("FSS Com Client version " + version);
		misc.Log.outError("Test des logs erreur...");
		
		thr_sender thrsend = new thr_sender();
		thrsend.start();
		
	}
}
