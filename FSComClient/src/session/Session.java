package session;

import java.util.Vector;

import misc.Log;

import socket.packet.handlers.sends.Ping_handler;


public class Session extends Thread{

	private static Integer status;
	private static Vector<group> groups;
	private static String pseudo;
	private static String perso_msg;
	
	public Session()
	{
		setStatus(0);
		setPseudo("");
		groups = new Vector<group>();
		setPerso_msg("");
	}
	
	public void run()
	{
		Update();
	}
	
	public static void Update()
	{
		while(true)
		{
			try 
			{
				Ping_handler pck = new Ping_handler();
				pck.Send();
				sleep(10000);
			}
			catch (InterruptedException e) 
			{
				Log.outError("Thread Error");
			}
		}
	}
	
	public static void CreateNewContact(contact cont)
	{
		for(group gr:groups)
			if(gr.getGid().equals(cont.getGroup()))
			{
				gr.AddContact(cont);
				return;
			}
	}
	
	public static void CreateNewGroup(group grp)
	{
		groups.add(grp);
	}
	
	public static contact getContactByUid(Integer _uid)
	{
		for(group g: getGroups())
			for(contact ct: g.getContacts())
				if(ct.getCid().equals(_uid))
					return ct;
		return null;
	}
	
	public static void setStatus(Integer st) { Session.status = st;	}
	public static Integer getStatus() {	return status; }
	public static void setPseudo(String pseudo) { Session.pseudo = pseudo; }
	public static String getPseudo() { return pseudo; }
	public static void setPerso_msg(String perso_msg) {	Session.perso_msg = perso_msg; }
	public static String getPerso_msg() { return perso_msg; }
	
	/*
	 * Action on groups
	 */
	
	public static Vector<group> getGroups() { return groups; }

	public static Integer getMaxGid()
	{
		Integer max_gid = 0;
		for(group g: groups)
			if(g.getGid() > max_gid)
				max_gid = g.getGid();
		
		return max_gid;
	}
	
	public static group getDefaultGroup()
	{
		for(group g: getGroups())
			if(g.getGid().equals(0))
				return g;

		return null;
	}
	
	public static void ClearGroups() { getGroups().clear(); }
	
	/*
	 * Actions on contact
	 */
	
	public static void ClearContacts() 
	{ 
		for(group gr:getGroups())
			gr.getContacts().clear();
	}
}
