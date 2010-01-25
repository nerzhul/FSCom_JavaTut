package socket.packet.handlers.listened;

import session.session;
import socket.packet.handlers.listen_handler;

public class PseudoToPlatform_handler extends listen_handler {

	public PseudoToPlatform_handler(session sess, Object packet) 
	{
		sess.ChangePseudo(packet);
	}

}
