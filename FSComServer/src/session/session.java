package session;

import java.sql.SQLException;
import java.util.Vector;

import database.DatabaseTransactions;

public class session {
	
	private boolean connected;
	private Integer status;
	private String name;
	private long uid;
	private Thread thr_associated;
	private Vector<session> sess_linked;
	
	public session(Thread thr)
	{
		uid = 0;
		connected = false;
		status = 0;
		name = "";
		thr_associated = thr;
		sess_linked = null;
	}
	
	public void connect_client() throws SQLException
	{
		connected = true;
		SessionHandler.AddSession(this);
		uid = DatabaseTransactions.LongQuery("account", "uid", "user = '" + name + "'");
	}
	
	public void disconnect_client()
	{
		SessionHandler.DestroySession(this,thr_associated);
	}

	public void contact_connected(session sess)
	{
		sess_linked.add(sess);
		/* TODO: all necessary actions to declare the client
		 connected*/
	}
	
	public boolean know_contact(long uid)
	{
		
		
		return false;
	}
	public void contact_disconnected(session sess)
	{
		/*
		 * TODO: contact client to say his contact was disconnected
		 */
		for(int i=0;i<sess_linked.size();i++)
			if(sess_linked.get(i).equals(sess))
				sess_linked.remove(i);
	}
	
	public Vector<session> getLinkedSessions() { return sess_linked; }
	public long getUid() { return uid; }
	public boolean IsConnected(){ return connected; }
	public void SetStatus(Integer st){ status = st; }
	public Integer getStatus(){	return status; }
	public void SetConnected(boolean cn) { connected = cn; } 
	public void SetName(String nm) { name = nm; }
	public String getName() { return name; }
}
