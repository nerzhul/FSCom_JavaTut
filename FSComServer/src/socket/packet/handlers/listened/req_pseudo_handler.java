package socket.packet.handlers.listened;

import database.DatabaseFunctions;
import session.session;
import socket.packet.handlers.listen_handler;
import socket.packet.handlers.senders.PseudoToClient_handler;

public class req_pseudo_handler extends listen_handler {

	public req_pseudo_handler(session sess, Object packet) 
	{
		data = packet.toString();
		PseudoToClient_handler pck = new PseudoToClient_handler(Integer.decode((String) data),
				DatabaseFunctions.getPseudoByUID(Integer.decode((String) data)));
		pck.Send(sess.getSocket());
	}


}
