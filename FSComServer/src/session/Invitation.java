package session;

import java.net.Socket;

import socket.packet.handlers.senders.invitation_handler;

import database.DatabaseTransactions;

public class Invitation {

	private Integer uid, uid_invited;
	public Invitation(Integer _uid, Integer uid_i, boolean connected) {
		uid = _uid;
		uid_invited = uid_i;
		
		if(!connected)
			RegisterInvitation();
	}

	
	private void RegisterInvitation()
	{
		DatabaseTransactions.ExecuteQuery("INSERT INTO acc_invitation VALUES ('" + uid_invited + "','" + uid + "'");
	}
	
	public void Send(Socket sock) 
	{
		invitation_handler invit = new invitation_handler(uid);
		invit.Send(sock);
	}

}
