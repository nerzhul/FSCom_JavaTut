package socket.packet.handlers.senders;

import session.session;
import socket.packet.handlers.Send_handler;

public class AddContact_handler extends Send_handler {

	public AddContact_handler(session sess, Object packet) 
	{
		opcode = 0x1B;
		data = sess.AddContact(packet);
	}

}
