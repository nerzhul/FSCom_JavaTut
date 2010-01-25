package socket.packet.handlers;

import session.session;

public class MsgPersoToPlatform_handler extends listen_handler {

	public MsgPersoToPlatform_handler(session sess, Object packet) 
	{
		sess.ChangeMsgPerso(packet);
	}

}
