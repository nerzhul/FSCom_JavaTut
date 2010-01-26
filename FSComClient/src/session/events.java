package session;

import session.objects.contact;
import session.objects.group;
import misc.Log;

public class events {

	
	public static void StoreStatus(Integer st)
	{
		Session.setStatus(st);
	}
	
	public static void StoreContacts(Object packet)
	{
		// TODO : store contacts into interface
		String tmp_contactlist[] = packet.toString().split("-|%|-");
		
		if(tmp_contactlist.length > 0 && !tmp_contactlist[0].equals("00"))
		{
			for(int i=0;i<tmp_contactlist.length;i++)
			{
				String tmp_contact[] = tmp_contactlist[i].split("-[%]-");
				if(tmp_contact.length != 7)
					Log.outError("Bad contact list packet !");
				else
				{
					// TODO : create an object
					contact tmp_con = new contact(Integer.decode(tmp_contact[0]),
							Integer.decode(tmp_contact[1]), tmp_contact[2],
							tmp_contact[5], tmp_contact[3], Integer.decode(tmp_contact[6]),
							Integer.decode(tmp_contact[4]));
					Session.CreateNewContact(tmp_con);
				}
			}
		}
	}

	public static void StoreGroups(Object packet) 
	{
		// TODO store groups into interface
		String tmp_grouplist[] = packet.toString().split("-|%|-");
		
		Session.CreateNewGroup(new group(0,"Autres contacts"));
		if(tmp_grouplist.length > 0 && !tmp_grouplist[0].equals("00"))
		{
			for(int i=0;i<tmp_grouplist.length;i++)
			{
				String tmp_group[] = tmp_grouplist[i].split("-|%|-");
				if(tmp_group.length != 2)
					Log.outError("Bad group list packet !");
				else
				{
					// TODO : create an object
					Session.CreateNewGroup(new group(Integer.decode(tmp_group[0]),tmp_group[1]));
				}
			}
		}
	}

	public static void ContactDisconnected(Object packet) {
		// TODO Auto-generated method stub
		
	}

	public static void ContactConnected(Object packet) {
		// TODO Auto-generated method stub
		
	}

	public static void BlockContact(Object packet) {
		// TODO Auto-generated method stub
		
	}

	public static void RecvMsg(Object packet) {
		// TODO Auto-generated method stub
		
	}

	public static void ContactModifyStatus(Object packet) {
		// TODO Auto-generated method stub
		
	}

	public static void ContactModifyPseudo(Object packet) {
		// TODO Auto-generated method stub
		
	}

	public static void ContactModifyPmsg(Object packet) {
		// TODO Auto-generated method stub
		
	}

	public static void ContactAdded(Object packet) {
		// TODO Auto-generated method stub
		
	}

	public static void ContactDeleted(Object packet) {
		// TODO Auto-generated method stub
		
	}

	public static void RecvInvitation(Object packet) {
		// TODO Auto-generated method stub
		
	}

	public static void ConnectionError() {
		// TODO Auto-generated method stub
		
	}

	public static void BadLoginInformations() {
		// TODO Auto-generated method stub
		
	}
	
	
}
