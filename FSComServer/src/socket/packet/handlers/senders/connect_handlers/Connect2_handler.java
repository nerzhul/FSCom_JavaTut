package socket.packet.handlers.senders.connect_handlers;

import session.session;
import socket.packet.handlers.Send_handler;
import socket.packet.objects.ClientDatas;

public class Connect2_handler extends Send_handler{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Connect2_handler(session sess, Object dat)
	{
		opcode = 0x22;
		sess.SetStatus((Integer) dat);
		sess.connect_client();
		data = new ClientDatas(sess);
	}
}
