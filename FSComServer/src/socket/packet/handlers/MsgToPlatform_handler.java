package socket.packet.handlers;

import session.session;


public class MsgToPlatform_handler extends listen_handler {

	public MsgToPlatform_handler(session sess, Object packet) {
		sess.TransmitMsgTo(packet);
	}

}
