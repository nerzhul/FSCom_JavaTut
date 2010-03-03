package socket.packet.handlers.senders.contact_handlers;

import session.session;
import socket.packet.handlers.Send_handler;

public class Status_handler extends Send_handler {

	public Status_handler(session sess, Object packet)
	{
		opcode = 0x0A;
		data = new String("01000");
		m_sess = sess;
		sess.SetStatus((Integer) packet);
		sess.broadcast_SomethingChanged(1);

	}
}
