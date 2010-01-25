package socket.packet.handlers.senders;

import session.session;
import socket.packet.handlers.send_handler;

public class AddContactWithoutInvite_handler extends send_handler {

	public AddContactWithoutInvite_handler(session sess, Object packet) 
	{
		opcode = 0x1B;
	}

}
