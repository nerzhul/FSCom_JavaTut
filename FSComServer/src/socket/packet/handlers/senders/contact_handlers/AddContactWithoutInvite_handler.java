package socket.packet.handlers.senders.contact_handlers;

import session.contact;
import session.session;
import socket.packet.handlers.Send_handler;

/*
 * contact added but no invite needed
 */
public class AddContactWithoutInvite_handler extends Send_handler {

	public AddContactWithoutInvite_handler(session sess, contact ct) 
	{
		opcode = 0x1B;
		data = ct;
	}
}
