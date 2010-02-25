package socket.packet.handlers.listened;

import session.session;
import socket.packet.handlers.Listen_handler;

public class MsgPersoToPlatform_handler extends Listen_handler {

	public MsgPersoToPlatform_handler(session sess, Object packet) 
	{
		sess.ChangeMsgPerso(packet);
	}

}
