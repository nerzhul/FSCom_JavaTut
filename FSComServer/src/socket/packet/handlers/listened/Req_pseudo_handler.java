package socket.packet.handlers.listened;

import database.DatabaseFunctions;
import session.session;
import socket.packet.handlers.Listen_handler;
import socket.packet.handlers.senders.PseudoToClient_handler;

public class Req_pseudo_handler extends Listen_handler {

	public Req_pseudo_handler(session sess, Object packet) 
	{
		data = packet.toString();
		PseudoToClient_handler pck = new PseudoToClient_handler(Integer.decode((String) data),
				DatabaseFunctions.getPseudoByUID(Integer.decode((String) data)));
		pck.Send(sess.getSocket());
	}


}
