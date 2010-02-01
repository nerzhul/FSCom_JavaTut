package socket.packet.handlers.senders;

import session.session;
import socket.packet.handlers.send_handler;

public class AddContact_handler extends send_handler {

	public AddContact_handler(session sess, Object packet) 
	{
		opcode = 0x1B;
		data = sess.AddContact(packet);
	}

}
