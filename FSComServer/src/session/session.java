package session;

import java.net.Socket;
import java.util.Vector;

import misc.Log;

import socket.packet.handlers.MsgPersoToClient_handler;
import socket.packet.handlers.MsgToClient_Handler;
import socket.packet.handlers.PseudoToClient_handler;
import socket.packet.handlers.StatusToClient_Handler;
import socket.packet.handlers.cont_connected_handler;
import socket.packet.handlers.cont_disconct_handler;

import database.DatabaseTransactions;

public class session {
	
	private boolean connected;
	private Integer status;
	private String name;
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
		thr_associated = thr;
		sess_linked = null;
		sock = sockt;
	}

	public void connect_client()
	{
		connected = true;
		SessionHandler.AddSession(this);
		uid = DatabaseTransactions.IntegerQuery("account", "uid", "user = '" + name + "'");
		personnal_msg = DatabaseTransactions.StringQuery("account", "phr_perso", "user = '" + name + "'");
	}
	
	public void disconnect_client()
	{
		SessionHandler.DestroySession(this,thr_associated);
	}

	public void contact_connected(session sess, boolean block)
	{
		if(!block)
			sess_linked.add(sess);
		cont_connected_handler pck = new cont_connected_handler(sess.getName(),
				sess.getStatus().toString(),sess.getPersonnalMsg(),sess.getUid());
		if(pck != null)
			pck.Send(sock);
	}
	
	public boolean know_contact(Integer _uid)
	{
		if(DatabaseTransactions.DataExist("acc_contact", "uid", "contact = '" + _uid + "'" +
				" AND uid = '" + uid + "'"))
			return true;
		else
			return false;
	}
	public void contact_disconnected(session sess, boolean blocked)
	{

		cont_disconct_handler pck = new cont_disconct_handler(sess.getUid());
		if(sock != null)
			pck.Send(sock);
		
		if(!blocked)
			for(int i=0;i<sess_linked.size();i++)
				if(sess_linked.get(i).equals(sess))
					sess_linked.remove(i);
	}
	
	public boolean has_blocked(Integer _uid) 
	{
		if(DatabaseTransactions.IntegerQuery("acc_contact", "blocked"
				, "contact = '" + _uid + "' AND uid = '" + uid + "'") == 1)
			return true;
		else
			return false;
	}
	
	public void broadcast_SomethingChanged(int sth) 
	{
		for(int i=0;i<sess_linked.size();i++)
		{
			boolean blocked = false;
			for(int j=0;j<uid_blocked.size();j++)
				if(uid_blocked.get(i) == sess_linked.get(i).getUid())
					blocked = true;
			
			if(!blocked)
			{
				switch(sth)
				{
					case 1:
						sess_linked.get(i).SendStatusToMe(uid, status);
						break;
					case 2:
						sess_linked.get(i).SendPseudoToMe(uid, name);
						break;
					case 3:
						sess_linked.get(i).SendMsgPersoToMe(uid, personnal_msg);
						break;
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

	public void block_contact(String c_uid, String method) 
	{
		if(method.equals("1"))
			contact_disconnected(getContactByUID(Integer.decode(c_uid)),true);
		else
			contact_connected(getContactByUID(Integer.decode(c_uid)), true);
		DatabaseTransactions.ExecuteQuery("UPDATE acc_contact SET blocked = '" + method + 
				"' where uid = '" + uid + "' AND contact = '" + c_uid + "'");
	}
	
	private session getContactByUID(Integer _uid)
	{
		session tmp_sess = null;
		for(int i=0;i<sess_linked.size();i++)
		{
			if(sess_linked.get(i).getUid() == _uid)
				tmp_sess = sess_linked.get(i);
		}
		return tmp_sess;
	}
	
	public void TransmitMsgTo(Object packet) {
		String cut_pck[] = packet.toString().split("#-%-#");
		if(cut_pck.length != 2)
		{
			Log.outError("Malformed Msg to Transmit");
			return;
		}
		
		Integer _uid = Integer.decode(cut_pck[0]);
		if(getContactByUID(_uid) != null)
			if(!getContactByUID(_uid).has_blocked(uid))
				getContactByUID(_uid).SendMessageToMe(uid,cut_pck[1]);
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
		String pck = packet.toString();
		SetName(pck);	
		DatabaseTransactions.ExecuteQuery("UPDATE account SET pseudo = '" + pck + 
				"'	WHERE uid = '" + uid + "'");
		broadcast_SomethingChanged(2);
	}
	
	public void ChangeMsgPerso(Object packet)
	{
		String pck = packet.toString();
		SetPersonnalMsg(pck);
		DatabaseTransactions.ExecuteQuery("UPDATE account SET phr_perso = '" + pck +
				"' WHERE uid = '" + uid + "'");
		broadcast_SomethingChanged(3);
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



	

	

	

	
}
