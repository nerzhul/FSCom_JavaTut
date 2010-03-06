package session;

import java.util.Vector;

import misc.Misc;

import database.DatabaseTransactions;

import socket.packet.objects.AccCreateDatas;

public class SessionHandler {

	private static Vector<session> v_sess;
	
	public SessionHandler()
	{
		v_sess = new Vector<session>();
	}

	public static void AddSession(session sess)
	{
		if(sess == null)
			return;
		synchronized(v_sess)
		{
			v_sess.add(sess);
			for(session s : v_sess)
				if(!s.equals(sess))
					if(s.know_contact(sess.getUid()) && !sess.has_blocked(s.getUid()))
						s.contact_connected(sess, false);
		}
	}

	public static session getContactByUID(Integer _uid)
	{
		synchronized(v_sess)
		{
			for(session s : v_sess)
				if(s.getUid().equals(_uid))
					return s;
		}
		return null;
	}
	
	public static boolean isConnected(Integer uid)
	{
		if(uid == 0)
			return false;
		
		if(getContactByUID(uid) == null)		
			return false;
		else
			return true;
	}

	public static void Update(int diff)
	{
		/* TODO: ping connected client and wait a response
			if no response, destroy the session and the listener thread
		 */
	}

	public static void DestroySession(session sess, Thread thr) 
	{
		synchronized(v_sess)
		{
			if(!v_sess.isEmpty())
			{
				for(session s: v_sess)
				{
					if(s.equals(sess))
						v_sess.remove(sess);
					else
					{
						if(s.know_contact(sess.getUid()))
							s.contact_disconnected(sess,false);
					}
				}
			}
		}
		thr.interrupt();
	}
	
	public static Integer AccountCreate(Object data)
	{
		if(Misc.isWrongType(data, new AccCreateDatas("","")))
			return 0;
		AccCreateDatas pck = (AccCreateDatas)data;
		String _user = pck.getName();
		String _pwd = pck.getPwd();
		Integer result = 0;
		if(!DatabaseTransactions.DataExist("account","user", "user = '" + _user + "'"))
		{
			DatabaseTransactions.ExecuteQuery("INSERT INTO account(user,sha_pass,pseudo) values ('" + _user +
					"','" + _pwd + "','" + _user + "')");
			result = 1;
		}
		return result;
	}
	
	public static String SearchAccountIPbyUid(Integer _uid)
	{
		synchronized(v_sess)
		{
			for(session s:v_sess)
			{
				if(s.getUid().equals(_uid))
					return s.getSocket().getInetAddress().toString();
			}
		}
		return null;
	}
}
