package socket.packet.handlers;

import session.session;

public class PseudoToPlatform_handler extends listen_handler {

	public PseudoToPlatform_handler(session sess, Object packet) {
		sess.ChangePseudo(packet);
	}

}
