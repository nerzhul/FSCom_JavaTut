package session;

import java.util.Vector;


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
				sleep(100);
			}
			catch (InterruptedException e) 
			{
				e.printStackTrace();
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
	
	public static void setStatus(Integer st) { Session.status = st;	}
	public static Integer getStatus() {	return status; }
	public static void setPseudo(String pseudo) { Session.pseudo = pseudo; }
	public static String getPseudo() { return pseudo; }
	public static void setPerso_msg(String perso_msg) {	Session.perso_msg = perso_msg; }
	public static String getPerso_msg() { return perso_msg; }
	public static Vector<group> getGroups() { return groups; }

	public static void ClearGroups() { getGroups().clear(); }
	public static void ClearContacts() 
	{ 
		for(group gr:getGroups())
			gr.getContacts().clear();
	}
}
