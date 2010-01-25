package socket.packet.handlers.listened;

import session.session;
import socket.packet.handlers.listen_handler;

public class MsgPersoToPlatform_handler extends listen_handler {

	public MsgPersoToPlatform_handler(session sess, Object packet) 
	{
		sess.ChangeMsgPerso(packet);
	}

}
