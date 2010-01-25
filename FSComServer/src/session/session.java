package session;

import java.net.Socket;
import java.sql.SQLException;
import java.util.Vector;

import misc.Log;

import socket.packet.handlers.MsgToClient_Handler;
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

	public void connect_client() throws SQLException
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

	public void contact_connected(session sess)
	{
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
	
	public void broadcast_StatusChanged() 
	{
		/* TODO send status to all contact not blocked
		 * 
		 */
	}
	
	public void block_contact(String c_uid, String method) 
	{
		contact_disconnected(getContactByUID(Integer.decode(c_uid)),true);
		// TODO : maj DB
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
		getContactByUID(_uid).SendMessageToMe(uid,cut_pck[1]);
	}
	
	private void SendMessageToMe(Integer _uid, String msg)
	{
		MsgToClient_Handler pck = new MsgToClient_Handler(_uid,msg);
		pck.Send(sock);		
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
