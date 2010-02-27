package thread;

import session.Session;
import socket.Sender;

public class threading extends Thread
{
	private static Sender thsend;
	private Session m_sess;
	private static windowthread m_window;
	private static thr_listen_p2p thp2p;

	public threading()	{}

	public void run()
	{
		LaunchSession(true);
		LaunchSwingInterface();
	}

	public static void LaunchSender(boolean new_thr)
	{
		if(new_thr)
			thsend = new Sender();
		thsend.start();
	}

	public static void StopSender()
	{
		if(thsend != null)
		{
			Sender.StopListener();
			thsend.interrupt();
		}
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

	public static void LaunchSwingInterface()
	{
		m_window = new windowthread();
		m_window.start();
	}

	public static void StopSwingInterface()
	{
		if(m_window != null)
			m_window.interrupt();
	}
	
	public static void Launchp2pListener()
	{
		if(thp2p != null)
			thp2p.interrupt();
		thp2p = new thr_listen_p2p();
		thp2p.start();
	}
	
	public static void Stopp2pListener()
	{
		if(thp2p != null)
			thp2p.interrupt();
	}
}
