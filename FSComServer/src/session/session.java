package session;

import java.net.Socket;
import java.util.Vector;

import javax.swing.ImageIcon;

import misc.Log;
import misc.Misc;
import socket.packet.handlers.senders.AvatarAnswer_handler;
import socket.packet.handlers.senders.connect_handlers.Cont_Connected_handler;
import socket.packet.handlers.senders.connect_handlers.Cont_Disconct_handler;
import socket.packet.handlers.senders.contact_handlers.AddContactWithoutInvite_handler;
import socket.packet.handlers.senders.contact_handlers.BlockContact_handler;
import socket.packet.handlers.senders.contact_handlers.ContactDeleted_handler;
import socket.packet.handlers.senders.contact_handlers.IPToClient_handler;
import socket.packet.handlers.senders.contact_handlers.MsgPersoToClient_handler;
import socket.packet.handlers.senders.contact_handlers.MsgToClient_Handler;
import socket.packet.handlers.senders.contact_handlers.PseudoToClient_handler;
import socket.packet.handlers.senders.contact_handlers.StatusToClient_Handler;
import socket.packet.handlers.senders.group_handlers.ConfirmGroupAdded_handler;
import socket.packet.handlers.senders.group_handlers.ConfirmGroupDeleted_handler;
import socket.packet.handlers.senders.group_handlers.ConfirmGroupRenamed_handler;
import socket.packet.objects.Avatar;
import socket.packet.objects.IdAndData;
import socket.packet.objects.Message;
import database.DatabaseFunctions;
import database.DatabaseTransactions;

public class session {
	
	private boolean connected;
	private Integer status;
	private String name;
	private String pseudo;
	private String personnal_msg;
	private Integer uid;
	private Thread thr_associated;
	private Vector<session> sess_linked;
	private Socket sock;
	private Vector<Integer> uid_blocked;
	
	public session(Thread thr, Socket sockt)
	{
		uid = 0;
		connected = false;
		status = 0;
		name = "";
		personnal_msg = "";
		setPseudo("");
		thr_associated = thr;
		sess_linked = new Vector<session>();
		sock = sockt;
	}

	public void connect_client()
	{
		connected = true;
		uid = DatabaseTransactions.IntegerQuery("account", "uid", "user = '" + name + "'");
		setPseudo(DatabaseTransactions.StringQuery("account", "pseudo", "user = '" + name + "'"));
		personnal_msg = DatabaseTransactions.StringQuery("account", "phr_perso", "user = '" + name + "'");
		
		SessionHandler.AddSession(this);
		LoadBlockedContacts();
		LoadAndSendInvitation();
	}
	
	private void LoadAndSendInvitation() 
	{
		Vector<Integer> invit = DatabaseTransactions.getIntegerList("acc_invitation", "contact", "uid = '" + uid + "'");
		for(Integer i: invit)
		{
			Invitation inv = new Invitation(i, uid, true);
			inv.Send(getSocket());
		}
	}

	private void LoadBlockedContacts() 
	{
		uid_blocked = DatabaseTransactions.getIntegerList("acc_blocked", "contact", "uid = '" + uid + "' AND blocked != '0'");
	}

	public void disconnect_client()
	{
		SessionHandler.DestroySession(this,thr_associated);
	}

	public void contact_connected(session sess, boolean block)
	{
		if(sess == null)
			return;
		
		synchronized(sess_linked)
		{
			if(!block)
				sess_linked.add(sess);
			sess.sess_linked.add(this);
		}
		Cont_Connected_handler pck = new Cont_Connected_handler(sess.getName(),
				sess.getStatus(),sess.getPersonnalMsg(),sess.getUid(),sess.getPseudo());
		if(pck != null)
			pck.Send(sock);
	}
	
	public boolean know_contact(Integer _uid)
	{
		if(DatabaseTransactions.DataExist("acc_contact", "uid", "contact = '" + _uid + "'" +
				" AND uid = '" + this.getUid() + "'"))
			return true;
		else
			return false;
	}
	
	public boolean has_group(Integer _gid)
	{
		if(DatabaseTransactions.DataExist("acc_group", "uid", "gid = '" + _gid + "'" +
				" AND uid = '" + uid + "'"))
			return true;
		else
			return false;
	}
	
	public synchronized void contact_disconnected(session sess, boolean blocked)
	{
		if(sess == null)
			return;
		
		Cont_Disconct_handler pck = new Cont_Disconct_handler(sess.getUid());
		if(sock != null)
			pck.Send(sock);
			if(!blocked)
			{
				for(int i=0;i<sess_linked.size();i++)
				//for(session s: sess_linked)
				{
					session s = sess_linked.get(i);
					if(s.equals(sess))
						sess_linked.remove(s);
				}
			}
	}
	
	public boolean has_blocked(Integer _uid) 
	{
		if(DatabaseTransactions.IntegerQuery("acc_blocked", "blocked"
				, "contact = '" + _uid + "' AND uid = '" + uid + "'") == 1)
			return true;
		else
			return false;
	}
	
	public void broadcast_SomethingChanged(int sth) 
	{
		switch(sth)
		{
			case 1:
				this.SendStatusToMe(0, status);
				break;
			case 2:
				this.SendPseudoToMe(0, name);
				break;
			case 3:
				this.SendMsgPersoToMe(0, personnal_msg);
				break;
		}
		
		synchronized(sess_linked)
		{
			for(session s : sess_linked)
			{
				
				boolean blocked = false;
				synchronized(uid_blocked)
				{
					for(Integer i : uid_blocked)
						if(i.equals(s.getUid()))
							blocked = true;
				}
				
				if(!blocked)
				{
					switch(sth)
					{
						case 1:
							s.SendStatusToMe(uid, status);
							break;
						case 2:
							s.SendPseudoToMe(uid, name);
							break;
						case 3:
							s.SendMsgPersoToMe(uid, personnal_msg);
							break;
					}
				}
			}
		}
	}
	
	private void SendMsgPersoToMe(Integer _uid, String pmsg) 
	{
		MsgPersoToClient_handler pck = new MsgPersoToClient_handler(_uid,pmsg);
		pck.Send(sock);
	}

	private void SendPseudoToMe(Integer _uid, String _name) 
	{
		PseudoToClient_handler pck = new PseudoToClient_handler(_uid,_name);
		pck.Send(sock);
	}

	public void block_contact(Object packet) 
	{
		if(Misc.isWrongType(packet, new IdAndData(0,"")))
			return;
		
		IdAndData pck = (IdAndData)packet;
		Integer method = Integer.decode(pck.getDat());
		Integer c_uid = pck.getUid();
		if(method.equals(1))
		{
			if(SessionHandler.getContactByUID(c_uid) != null)
				SessionHandler.getContactByUID(c_uid).contact_disconnected(this,true);
			synchronized(uid_blocked)
			{
				uid_blocked.add(c_uid);
			}
		}
		else
		{
			if(SessionHandler.getContactByUID(c_uid) != null)
				SessionHandler.getContactByUID(c_uid).contact_connected(this, true);
			synchronized(uid_blocked)
			{	
				uid_blocked.remove(c_uid);
			}
		}
		
		if(DatabaseTransactions.DataExist("acc_blocked", "blocked", "uid = '" + 
				uid + "' AND contact = '" + c_uid + "'"))
			DatabaseTransactions.ExecuteQuery("UPDATE acc_blocked SET blocked = '" + method + 
					"' where uid = '" + uid + "' AND contact = '" + c_uid + "'");
		else
			DatabaseTransactions.ExecuteQuery("INSERT INTO acc_blocked VALUES ('" + uid + "','" 
					+ c_uid + "','" + method + "')");
		BlockContact_handler pack = new BlockContact_handler(c_uid,method);
		pack.Send(sock);
	}
	
	public void TransmitMsgTo(Object packet) {
		if(Misc.isWrongType(packet, new Message(null,null)))
			return;
		
		Message msg = (Message) packet;
		Integer _uid = msg.getDest();
		if(SessionHandler.getContactByUID(_uid) != null)
			if(!SessionHandler.getContactByUID(_uid).has_blocked(uid))
				SessionHandler.getContactByUID(_uid).SendMessageToMe(uid,msg.getMsg());
	
	}
	
	private void SendMessageToMe(Integer _uid, String msg)
	{
		MsgToClient_Handler pck = new MsgToClient_Handler(_uid,msg);
		pck.Send(sock);		
	}

	private void SendStatusToMe(Integer _uid, Integer status)
	{
		StatusToClient_Handler pck = new StatusToClient_Handler(_uid,status);
		pck.Send(sock);
	}
	
	public void ChangePseudo(Object packet) 
	{
		if(Misc.isWrongType(packet, new String()))
			return;
		
		String pck = packet.toString();
		SetName(pck);
		DatabaseTransactions.ExecuteQuery("UPDATE account SET pseudo = '" + pck + 
				"'	WHERE uid = '" + uid + "'");
		broadcast_SomethingChanged(2);
	}
	
	public void ChangeMsgPerso(Object packet)
	{
		if(Misc.isWrongType(packet, new String()))
			return;
		
		String pck = packet.toString();
		SetPersonnalMsg(pck);
		DatabaseTransactions.ExecuteQuery("UPDATE account SET phr_perso = '" + pck +
				"' WHERE uid = '" + uid + "'");
		broadcast_SomethingChanged(3);
	}
	
	public contact AddContact(Object packet) 
	{
		if(Misc.isWrongType(packet, new String()))
			return null;
		
		String username = packet.toString();
		if(DatabaseTransactions.DataExist("account", "user", "user = '" + username + "'") &&
				!username.equals(DatabaseFunctions.getAccountNameByUID(uid)))
		{
			Integer _uid = DatabaseFunctions.getAccountUIDByName(username);
			if(!DatabaseTransactions.DataExist("acc_contact", "contact", "uid = '" + uid + "' AND contact = '"
					+ _uid + "'"))
			{
				DatabaseTransactions.ExecuteQuery("INSERT INTO acc_contact VALUES ('" + uid + "','" + 
						_uid + "','','0','0')");
				
				if(SessionHandler.isConnected(_uid))
				{
					Invitation invit = new Invitation(uid, _uid, true);
					invit.Send(SessionHandler.getContactByUID(_uid).getSocket());
				}
				else
					new Invitation(uid,_uid,false);
				
				return new contact(_uid, 0, DatabaseTransactions.StringQuery(
						"account", "pseudo", "uid = '" + _uid + "'"), 
						DatabaseTransactions.StringQuery(
								"account", "phr_perso", "uid = '" + _uid + "'"),
						DatabaseTransactions.StringQuery("acc_contact", "comment",
						"contact = '" + _uid + "'"), 0, 0,username);
			}
			else
				return null;
		}
		else
			return null;
	}

	public void ManageInvitation(Integer _uid, Integer method) 
	{
		switch(method)
		{
			case 1:
				DatabaseTransactions.ExecuteQuery("DELETE FROM acc_invitation WHERE uid = '" + uid + "' AND" +
						" contact = '" + _uid + "'");
				break;
			case 0:
				DatabaseTransactions.ExecuteQuery("DELETE FROM acc_invitation WHERE uid = '" + uid + "' AND" +
						" contact = '" + _uid + "'");
				DatabaseTransactions.ExecuteQuery("INSERT INTO acc_contact VALUES ('" + _uid + "','" + 
						uid + "','0','','0'");
				AddContactToClientList(_uid);
				if(SessionHandler.getContactByUID(_uid).IsConnected())
				{
					SendStatusToMe(_uid,SessionHandler.getContactByUID(_uid).getStatus());
					SessionHandler.getContactByUID(_uid).SendStatusToMe(uid, this.getStatus());
				}
				
				break;
			default:
				Log.outError("Invalid response from ManageInvitation");
				break;
		}
	}
	
	private void AddContactToClientList(Integer _uid) 
	{
		AddContactWithoutInvite_handler ACWI = new AddContactWithoutInvite_handler(this,new contact(_uid, 0, DatabaseTransactions.StringQuery(
				"account", "pseudo", "uid = '" + _uid + "'"), 
				DatabaseTransactions.StringQuery(
						"account", "phr_perso", "uid = '" + _uid + "'"),
				DatabaseTransactions.StringQuery("acc_contact", "comment",
				"contact = '" + _uid + "'"), 0, 0,DatabaseTransactions.StringQuery("account","user", "uid = '" + _uid + "'")));
		ACWI.Send(sock);
	}
	
	public synchronized void DelContact(Integer _uid, Integer blocked) 
	{
		DatabaseTransactions.ExecuteQuery("DELETE FROM acc_contact WHERE uid = '" + uid + "' AND " 
				+ "contact = '" + _uid + "'");
		DatabaseTransactions.ExecuteQuery("DELETE FROM acc_invitation WHERE contact = '" + uid + "' AND "
				+ "uid = '" + _uid + "'");
		if(SessionHandler.isConnected(_uid))
		{
			for(session s : sess_linked)
				if(s.getUid().equals(_uid))
					sess_linked.remove(s);
			SessionHandler.getContactByUID(_uid).contact_disconnected(this, true);
		}
		ContactDeleted_handler pack = new ContactDeleted_handler(_uid);
		pack.Send(getSocket());
	}
	
	public void EventContactGroupChange(Object data) 
	{
		if(Misc.isWrongType(data, new IdAndData(0,"")))
			return;
		
		IdAndData dat = (IdAndData) data;
		if(dat.getUid() > 0)
			if(know_contact(dat.getUid()))
			{
				Integer tmpgid = Integer.decode(dat.getDat());
				if(tmpgid >= 0)
					if(has_group(tmpgid) || tmpgid.equals(0))
						DatabaseTransactions.ExecuteUQuery("acc_contact", "group",
							tmpgid.toString(), " contact = '" + dat.getUid() + "'");
					
			}
	}
	
	public Vector<session> getLinkedSessions() { return sess_linked; }
	public Integer getUid() { return uid; }
	public boolean IsConnected(){ return connected; }
	public void SetStatus(Integer st){ status = st; }
	public Integer getStatus(){	return status; }
	public void SetConnected(boolean cn) { connected = cn; } 
	public void SetName(String nm) { name = nm; }
	public String getName() { return name; }
	public String getPersonnalMsg() { return personnal_msg; }
	public void SetPersonnalMsg(String msg) { personnal_msg = msg; }
	public Socket getSocket() { return sock; }
	public void setPseudo(String pseudo) { this.pseudo = pseudo; }
	public String getPseudo() { return pseudo; }

	public void EventGroupAdd(Object data) 
	{
		if(Misc.isWrongType(data, new IdAndData(0,"")))
			return;
		
		IdAndData pck = (IdAndData) data;
		if(pck.getUid().equals(0))
			return;
		
		DatabaseTransactions.ExecuteQuery("INSERT INTO acc_group VALUES ('" +
				this.getUid() + "','" + pck.getUid() + "','" + pck.getDat() + "')");
		
		ConfirmGroupAdded_handler pkt = new ConfirmGroupAdded_handler(pck);
		pkt.Send(sock);
	}

	public void EventGroupDel(Object data) 
	{
		if(Misc.isWrongType(data, new Integer(0)))
			return;
		
		Integer _gid = (Integer)data;
		if(_gid.equals(0))
			return;
		
		DatabaseTransactions.ExecuteQuery("DELETE FROM acc_group where uid = '" +
				this.getUid() + "' AND gid = '" + _gid + "'");
		DatabaseTransactions.ExecuteUQuery("acc_contact", "group", "0", "uid = '" +
				this.getUid() + "' AND `group` = '" + _gid + "'");
		
		ConfirmGroupDeleted_handler pkt = new ConfirmGroupDeleted_handler(_gid);
		pkt.Send(sock);
	}

	public void EventGroupRen(Object data) 
	{
		if(Misc.isWrongType(data, new IdAndData(0,"")))
			return;
		
		IdAndData pck = (IdAndData)data;
		Integer _gid = pck.getUid();
		String gName = pck.getDat();
		
		DatabaseTransactions.ExecuteUQuery("acc_group", "name", gName, "uid = '" +
				this.getUid() + "' AND gid = '" + _gid + "'");
		ConfirmGroupRenamed_handler pkt = new ConfirmGroupRenamed_handler(_gid,gName);
		pkt.Send(sock);
	}

	public void SearchIp(Object data) 
	{
		// Unused packet
		Integer _uid = Integer.decode(data.toString());
		
		String IP = SessionHandler.SearchAccountIPbyUid(_uid);
		if(IP != null)
		{
			IPToClient_handler pck = new IPToClient_handler(_uid,IP);
			pck.Send(sock);
		}
	}

	public void EventReqAvatar(Object data) 
	{
		if(Misc.isWrongType(data, new IdAndData(0,"")))
			return;
		
		IdAndData pck = (IdAndData)data;
		Integer _uid = pck.getUid();
		AvatarAnswer_handler pkt = new AvatarAnswer_handler(AvatarHandler.getAvatarByUID(_uid));
		pkt.Send(sock);
	}

	public void EventStoreAvatar(Object data) 
	{
		if(Misc.isWrongType(data, new Avatar(0,new ImageIcon())))
			return;
		
		Avatar pck = (Avatar)data;
		pck.setUid(this.getUid());
		
		if(pck != null)
			AvatarHandler.AddAvatar(pck);
	}

	

	
	



	

	

	

	
}
