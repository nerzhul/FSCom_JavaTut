package socket.packet.handlers.senders;

import session.session;
import socket.packet.handlers.send_handler;
import socket.packet.objects.ClientDatas;

public class connect2_handler extends send_handler{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public connect2_handler(session sess, Object dat)
	{
		opcode = 0x22;
		sess.connect_client();
		sess.SetStatus((Integer) dat);
		data = new ClientDatas(sess);
		
	}
}
