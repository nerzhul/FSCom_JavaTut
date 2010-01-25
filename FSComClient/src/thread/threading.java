package thread;

import session.Session;
import socket.sender;

public class threading extends Thread{

	private sender thsend;
	private Session m_sess;
	
	public threading()
	{
		thsend = new sender();
	}
	
	public void run()
	{
		LaunchSender(true);
		LaunchSession(true);
		LaunchSwingInterface();
	}

	public void LaunchSender(boolean new_thr)
	{
		if(new_thr)
			thsend = new sender();
		thsend.start();
	}
	
	public void StopSender()
	{
		if(thsend != null)
			thsend.interrupt();
	}
	
	public void LaunchSession(boolean new_thr)
	{
		if(new_thr)
			m_sess = new Session();
		m_sess.start();
	}
	
	public void StopSession()
	{
		if(m_sess != null)
			m_sess.interrupt();
	}
	
	public void LaunchSwingInterface()
	{
	}
	
	public void StopSwingInterface()
	{
	}
}
