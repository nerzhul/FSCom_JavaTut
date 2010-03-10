package session;

import java.util.Vector;

import misc.Log;

import socket.packet.handlers.sends.Ping_handler;

public class Session extends Thread{

	private static Integer status;
	private static Vector<group> groups;
	private static String pseudo;
	private static String perso_msg;
	
	//fonction définissant l'objet session
	public Session()
	{
		setStatus(0);//avec un statut(offline)
		setPseudo("");//un pseudo(vide)
		groups = new Vector<group>();//un vecteur de groupe (contenant les contacts)
		setPerso_msg("");//un message perso (vide)
	}
	
	//on lance le thread
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
	
	//fonction pour la création d'un contact
	public static void CreateNewContact(contact cont)
	{
		for(group gr:groups)
			if(gr.getGid().equals(cont.getGroup()))
			{
				gr.AddContact(cont);//on l'ajout au groupe
				return;
			}
	}
	
	//fonction pour la création de groupe
	public static void CreateNewGroup(group grp)
	{
		groups.add(grp);//on l'ajoute au vecteur de groupe
	}
	
	//fonction pour afficher les contacts dans l'ordre de leur uid
	public static contact getContactByUid(Integer _uid)
	{
		for(group g: getGroups())
			for(contact ct: g.getContacts())
				if(ct.getCid().equals(_uid))
					return ct;
		return null;
	}
	//fonctions d'accès aux variables
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
