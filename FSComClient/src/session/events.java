package session;

import java.util.Vector;

import javax.swing.JOptionPane;

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
	
	public static void StoreContacts(Vector<contact> packet)
	{
		Session.ClearContacts();
		for(contact ct: packet)
			Session.CreateNewContact(ct);
	}

	public static void StoreGroups(Vector<group> packet) 
	{
		Session.ClearGroups();
		
		Session.CreateNewGroup(new group(0,"Autres contacts"));
		for(group gr:packet)
			Session.CreateNewGroup(gr);
	}

	public static void ContactDisconnected(Object packet) 
	{
		for(group g : Session.getGroups())
			for(contact ct : g.getContacts())
				if(ct.getCid().equals(Integer.decode(packet.toString())))
				{
					// TODO: declare contact disconnected to client
					return;
				}
		
	}

	public static void ContactConnected(Object packet) 
	{
		for(group g : Session.getGroups())
			for(contact ct : g.getContacts())
				if(ct.getCid().equals(Integer.decode(packet.toString())))
				{
					// TODO: declare contact connected to client
					return;
				}
	}

	public static void BlockContact(Object packet) 
	{
		for(group g : Session.getGroups())
			for(contact ct : g.getContacts())
				if(ct.getCid().equals(Integer.decode(packet.toString())))
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
		// TODO : handle with windows
	}

	public static void ContactModifyStatus(Object packet) 
	{
		// TODO : split the packet
		for(group g : Session.getGroups())
			for(contact ct : g.getContacts())
				if(ct.getCid().equals(Integer.decode(packet.toString())))
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
			for(group g : Session.getGroups())
				for(contact ct : g.getContacts())
					if(ct.getCid().equals(pck.getUid()))
					{
						ct.setPseudo(pck.getDat());
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
			for(group g : Session.getGroups())
				for(contact ct : g.getContacts())
					if(ct.getCid().equals(pck.getUid()))
					{
						// TODO: modify contact Pmsg
						return;
					}
		}
	}

	public static void ContactAdded(Object packet) 
	{
		// TODO Auto-generated method stub
		
	}

	public static void ContactDeleted(Object packet) 
	{
		// TODO: split the packet
		for(group g : Session.getGroups())
			for(contact ct : g.getContacts())
				if(ct.getCid().equals(Integer.decode(packet.toString())))
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

	public static void StoreAllDatas(Object packet) 
	{
		if(!packet.getClass().equals((new ClientDatas()).getClass()))
		{
			Log.outError("Malformed Data Received");
			return;
		}
		ClientDatas pck = (ClientDatas) packet;
		Session.setPerso_msg(pck.getPperso());
		Session.setPseudo(pck.getPseudo());
		events.StoreGroups(pck.GetMyGroups());
		events.StoreContacts(pck.GetMyContacts());
	}
}
