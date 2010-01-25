package session;

import java.util.Vector;

import session.objects.contact;
import session.objects.group;

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
			
		}
	}
	
	public static void CreateNewContact(contact cont)
	{
		for(int i=0;i<groups.size();i++)
			if(groups.get(i).getGid() == cont.getGroup())
			{
				groups.get(i).AddContact(cont);
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
}
