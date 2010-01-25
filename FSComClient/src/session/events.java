package session;

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
				if(tmp_contact.length != 4)
					Log.outError("Bad contact list packet !");
				else
				{
					// TODO : create an object
					Session.CreateNewContact(new Object());
				}
			}
		}
	}

	public static void StoreGroups(Object packet) 
	{
		// TODO store groups into interface
		String tmp_grouplist[] = packet.toString().split("-|%|-");
		
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
					Session.CreateNewGroup(new Object());
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
	
	
}
