package socket.packet.handlers.senders;

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
		sess.connect_client();
		sess.SetStatus((Integer) dat);
		data = new ClientDatas(sess);
		
	}
}
