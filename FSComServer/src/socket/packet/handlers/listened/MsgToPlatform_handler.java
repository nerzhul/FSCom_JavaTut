package socket.packet.handlers.listened;

import session.session;
import socket.packet.handlers.Listen_handler;


public class MsgToPlatform_handler extends Listen_handler {

	public MsgToPlatform_handler(session sess, Object packet) {
		sess.TransmitMsgTo(packet);
	}

}
