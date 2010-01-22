package session;

import java.util.Vector;

public class SessionHandler {

	private static Vector<session> v_sess;
	
	public static void AddSession(session sess)
	{
		if(sess != null)
			v_sess.add(sess);
	}
	public static void Update(int diff)
	{
		/* TODO: ping connected client and wait a response
			if no response, destroy the session and the listener thread
		  
		 */
	}
	public static void DestroySession(session sess, Thread thr) 
	{
		/* TODO: send packet to all client which refers to 
		 * this session to declare client disconnected
		 */
		for(int i=0;i<v_sess.size();i++)
		{
			if(v_sess.get(i).equals(sess))
				v_sess.remove(i);
			else
			{
				if(v_sess.get(i).know_contact(sess.getUid()))
					v_sess.get(i).contact_disconnected(sess);
			}
		}
		
		// delete the listener on the session
		thr.interrupt();
	}
}
