package socket.packet.handlers.senders.contact_handlers;

import session.session;
import socket.packet.handlers.Send_handler;

/*
 * contact added with his data
 */
public class AddContact_handler extends Send_handler {

	public AddContact_handler(session sess, Object packet) 
	{
		opcode = 0x1B;
		data = sess.AddContact(packet);
	}

}
