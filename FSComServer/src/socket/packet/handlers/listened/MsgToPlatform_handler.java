package socket.packet.handlers.listened;

import session.session;
import socket.packet.handlers.listen_handler;


public class MsgToPlatform_handler extends listen_handler {

	public MsgToPlatform_handler(session sess, Object packet) {
		sess.TransmitMsgTo(packet);
	}

}
