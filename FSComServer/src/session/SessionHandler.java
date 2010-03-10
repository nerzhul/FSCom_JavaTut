package session;

import java.util.Vector;

import misc.Misc;
import socket.packet.objects.AccCreateDatas;
import database.DatabaseTransactions;

public class SessionHandler {

	private static Vector<session> v_sess;
	
	public SessionHandler()
	{
		v_sess = new Vector<session>();
	}

	public synchronized static void DisconnectAllClients()
	{
		// consult the vector and destroy all sessions
		//for(session s: v_sess)
		for(int i=0;i<v_sess.size();i++)
		{
			session s = v_sess.get(i);
			s.disconnect_client();
		}
			
	}
	
	public synchronized static void AddSession(session sess)
	{
		if(sess == null)
			return;

		/*
		 *  add the session to vector and declare him connected to all contacts
		 *  if the contact is'nt blocked
		 */
		v_sess.add(sess);
		for(session s : v_sess)
			if(!s.equals(sess))
				if(s.know_contact(sess.getUid()) && !sess.has_blocked(s.getUid()))
					s.contact_connected(sess, false);
	}

	public synchronized static session getContactByUID(Integer _uid)
	{
		// get contact for an user id
		for(session s : v_sess)
			if(s.getUid().equals(_uid))
				return s;
		return null;
	}
	
	public static boolean isConnected(Integer uid)
	{
		if(uid == 0)
			return false;
		// check if contact is connnected
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

	public synchronized static void DestroySession(session sess, Thread thr) 
	{
 
		if(v_sess.isEmpty())
			return;
		
		for(int i=0;i<v_sess.size();i++)
		//for(session s: v_sess)
		{
			// search client and remove him, else declare him disconnected
			session s = v_sess.get(i);
			if(s.equals(sess))
				v_sess.remove(sess);
			else
			{
				if(s.know_contact(sess.getUid()))
					s.contact_disconnected(sess,false);
			}
		}
		thr.interrupt();
	}
	
	public static Integer AccountCreate(Object data)
	{
		// 0: failed  1: success
		if(Misc.isWrongType(data, new AccCreateDatas("","")))
			return 0;
		
		AccCreateDatas pck = (AccCreateDatas)data;
		String _user = pck.getName();
		String _pwd = pck.getPwd();
		
		// if account does'nt exist, create it
		if(!DatabaseTransactions.DataExist("account","user", "user = '" + _user + "'"))
		{
			DatabaseTransactions.ExecuteQuery("INSERT INTO account(user,sha_pass,pseudo) values ('" + _user +
					"','" + _pwd + "','" + _user + "')");
			return 1;
		}
		return 0;
	}
	
	public synchronized static String SearchAccountIPbyUid(Integer _uid)
	{
		// search ip by socket
		for(session s:v_sess)
			if(s.getUid().equals(_uid))
				return s.getSocket().getInetAddress().toString();
		return null;
	}
}
