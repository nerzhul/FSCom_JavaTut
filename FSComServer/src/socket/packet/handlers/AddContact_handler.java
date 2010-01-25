package socket.packet.handlers;

import session.session;

public class AddContact_handler extends send_handler {

	public AddContact_handler(session sess, Object packet) 
	{
		opcode = 0x1B;
		data = sess.AddContact(packet).toString();
	}

}
