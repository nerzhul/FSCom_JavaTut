package session;

import session.objects.contact;
import session.objects.group;
import socket.sender;
import thread.threading;
import windows.forms.form_contact;
import misc.Log;

public class events {

	
	public static void StoreStatus(Integer st)
	{
		Session.setStatus(st);
	}
	
	public static void StoreContacts(Object packet)
	{
		// TODO : store contacts into interface
		String tmp_contactlist[] = packet.toString().split("///.///");
		
		if(tmp_contactlist.length > 0 && !tmp_contactlist[0].equals("00"))
		{
			for(int i=0;i<tmp_contactlist.length-1;i++)
			{
				String tmp_contact[] = tmp_contactlist[i].split("//.//");
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
		
		String tmp_grouplist[] = packet.toString().split("/./.");
		
		Session.CreateNewGroup(new group(0,"Autres contacts"));
		if(tmp_grouplist.length > 0 && !tmp_grouplist[0].equals("00"))
		{
			for(int i=0;i<tmp_grouplist.length-1;i++)
			{
				String tmp_group[] = tmp_grouplist[i].split("/.");
				if(tmp_group.length != 2)
					Log.outError("Bad group list packet !");
				else
					Session.CreateNewGroup(new group(Integer.decode(tmp_group[0]),tmp_group[1]));
			}
		}
	}

	public static void ContactDisconnected(Object packet) 
	{
		for(int i=0;i<Session.getGroups().size();i++)
			for(int j=0;j<Session.getGroups().get(i).getContacts().size();j++)
				if(Session.getGroups().get(i).getContacts().get(j).getCid().equals(Integer.decode(packet.toString())))
				{
					// TODO: declare contact disconnected to client
					return;
				}
		
	}

	public static void ContactConnected(Object packet) 
	{
		for(int i=0;i<Session.getGroups().size();i++)
			for(int j=0;j<Session.getGroups().get(i).getContacts().size();j++)
				if(Session.getGroups().get(i).getContacts().get(j).getCid().equals(Integer.decode(packet.toString())))
				{
					// TODO: declare contact connected to client
					return;
				}
	}

	public static void BlockContact(Object packet) 
	{
		// TODO: split the packet
		for(int i=0;i<Session.getGroups().size();i++)
			for(int j=0;j<Session.getGroups().get(i).getContacts().size();j++)
				if(Session.getGroups().get(i).getContacts().get(j).getCid().equals(Integer.decode(packet.toString())))
				{
					// TODO: declare contact blocked to client
					return;
				}
	}

	public static void RecvMsg(Object packet) {
		// TODO Auto-generated method stub
		
	}

	public static void ContactModifyStatus(Object packet) 
	{
		// TODO : split the packet
		for(int i=0;i<Session.getGroups().size();i++)
			for(int j=0;j<Session.getGroups().get(i).getContacts().size();j++)
				if(Session.getGroups().get(i).getContacts().get(j).getCid().equals(Integer.decode(packet.toString())))
				{
					// TODO: modify contact status
					return;
				}
		
	}

	public static void ContactModifyPseudo(Object packet) 
	{
		// TODO: split the packet
		for(int i=0;i<Session.getGroups().size();i++)
			for(int j=0;j<Session.getGroups().get(i).getContacts().size();j++)
				if(Session.getGroups().get(i).getContacts().get(j).getCid().equals(Integer.decode(packet.toString())))
				{
					// TODO: modify contact pseudo
					return;
				}
	}

	public static void ContactModifyPmsg(Object packet) 
	{
		// TODO: split the packet
		for(int i=0;i<Session.getGroups().size();i++)
			for(int j=0;j<Session.getGroups().get(i).getContacts().size();j++)
				if(Session.getGroups().get(i).getContacts().get(j).getCid().equals(Integer.decode(packet.toString())))
				{
					// TODO: modify contact Pmsg
					return;
				}
	}

	public static void ContactAdded(Object packet) {
		// TODO Auto-generated method stub
		
	}

	public static void ContactDeleted(Object packet) 
	{
		// TODO: split the packet
		for(int i=0;i<Session.getGroups().size();i++)
			for(int j=0;j<Session.getGroups().get(i).getContacts().size();j++)
				if(Session.getGroups().get(i).getContacts().get(j).getCid().equals(Integer.decode(packet.toString())))
				{
					// TODO: delete the contact from vector and refresh list.
					return;
				}
	}

	public static void RecvInvitation(Object packet) 
	{
		//TODO: show a msgbox to choice how to handle the invitation
	}

	public static void ConnectionError() 
	{
		Log.ShowPopup("Erreur de connexion avec le serveur", true);
	}

	public static void BadLoginInformations() 
	{
		Log.ShowPopup("Login incorrect", true);
	}

	public static void ShowConnectedFrame() {
		Log.ShowPopup("Connecté !", false);
		if(Session.getFmc() == null)
			Session.setFmc(new form_contact());
	}

	public static void DisconnectCurrentClient() 
	{
		sender.StopSocket();
		threading.StopSender();
	}
	
	
}
