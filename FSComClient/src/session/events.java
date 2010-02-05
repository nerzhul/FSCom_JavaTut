package session;

import javax.swing.JOptionPane;

import session.objects.contact;
import session.objects.group;
import socket.sender;
import socket.packet.handlers.sends.Answer_Invit_handler;
import socket.packet.objects.ClientDatas;
import socket.packet.objects.IdAndData;
import socket.packet.objects.message;
import thread.threading;
import windows.windowthread;
import misc.Log;

public class events {

	
	public static void StoreStatus(Integer st)
	{
		Session.setStatus(st);
	}
	
	public static void StoreContacts(Object packet)
	{
		Session.ClearContacts();
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
		String tmp_grouplist[] = packet.toString().split("/./.");
		Session.ClearGroups();
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
		if(!packet.getClass().equals((new message(null,null)).getClass()))
		{
			Log.outError("Malformed Msg to Transmit");
			return;
		}
		message msg = (message) packet;
		Integer _uid = msg.getDest();
		Log.outError(_uid + msg.getMsg());
		
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
		if(!packet.getClass().equals((new IdAndData(null,null)).getClass()))
		{
			Log.outError("Malformed Data Received");
			return;
		}
		
		IdAndData pck = (IdAndData) packet;
		if(pck.getUid().equals(0))
		{
			if(windowthread.getFmConn() != null)
				if(windowthread.getFmConn().getPanContact() != null)
					windowthread.getFmConn().getPanContact().ChPseudo(pck.getDat());
		}
		else
		{
		for(int i=0;i<Session.getGroups().size();i++)
			for(int j=0;j<Session.getGroups().get(i).getContacts().size();j++)
				if(Session.getGroups().get(i).getContacts().get(j).getCid().equals(pck.getUid()))
				{
					// TODO: modify contact pseudo
					return;
				}
		}
	}

	public static void ContactModifyPmsg(Object packet) 
	{
		if(!packet.getClass().equals((new IdAndData(null,null)).getClass()))
		{
			Log.outError("Malformed Data Received");
			return;
		}
		
		IdAndData pck = (IdAndData) packet;
		if(pck.getUid().equals(0))
			; // TODO: modify my personal phrase on client
		else
		{
			for(int i=0;i<Session.getGroups().size();i++)
				for(int j=0;j<Session.getGroups().get(i).getContacts().size();j++)
					if(Session.getGroups().get(i).getContacts().get(j).getCid().equals(pck.getUid()))
					{
						// TODO: modify contact Pmsg
						return;
					}
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
		String[] dat = packet.toString().split("[][]");
		if(dat.length != 2)
			Log.outError("Bad invitation received !");
		else
		{
			Integer answer = JOptionPane.showConfirmDialog(null, dat[1] + " vous a ajouté, voulez vous l'accepter ?","Nouveau contact !",JOptionPane.YES_NO_CANCEL_OPTION);
			Answer_Invit_handler arh = new Answer_Invit_handler(Integer.decode(dat[0]), answer);
			arh.Send();
		}
	}

	public static void ConnectionError() 
	{
		Log.ShowPopup("Erreur de connexion avec le serveur", true);
	}

	public static void BadLoginInformations() 
	{
		Log.ShowPopup("Login incorrect", true);
	}

	public static void ShowConnectedFrame() 
	{
		windowthread.SwitchPanel(2);
	}

	public static void DisconnectCurrentClient() 
	{
		sender.StopSocket();
		threading.StopSender();
	}

	public static void StoreAllDatas(Object packet) {
		if(!packet.getClass().equals((new ClientDatas()).getClass()))
		{
			Log.outError("Malformed Data Received");
			return;
		}
		ClientDatas pck = (ClientDatas) packet;
		Session.setPerso_msg(pck.getPperso());
		Session.setPseudo(pck.getPseudo());
		
	}
	
	
}
