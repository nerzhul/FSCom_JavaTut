package session;

import java.net.Socket;

import socket.packet.handlers.senders.Invitation_handler;

import database.DatabaseTransactions;

/*
 * Invitation : when client invite another
 */
public class Invitation {

	// basic constructor
	private Integer uid, uid_invited;
	public Invitation(Integer _uid, Integer uid_i, boolean connected) {
		uid = _uid;
		uid_invited = uid_i;
		
		// if client is'nt connected we need to register it
		if(!connected)
			RegisterInvitation();
	}

	private void RegisterInvitation()
	{
		/*
		 * we register if client is'nt connected. IGNORE permit 
		 * to not allow multi invitation
		 */ 
		DatabaseTransactions.ExecuteQuery("INSERT IGNORE INTO acc_invitation VALUES ('" + uid_invited + "','" + uid + "')");
	}
	
	public void Send(Socket sock) 
	{
		Invitation_handler invit = new Invitation_handler(uid);
		invit.Send(sock);
	}

}
