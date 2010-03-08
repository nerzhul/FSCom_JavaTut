package socket.packet.handlers.senders.contact_handlers;

import session.contact;
import session.session;
import socket.packet.handlers.Send_handler;

public class AddContactWithoutInvite_handler extends Send_handler {

	public AddContactWithoutInvite_handler(session sess, contact packet) 
	{
		opcode = 0x1B;
		data = packet;
	}

}
