package socket.packet.handlers.listened;

import misc.Misc;
import database.DatabaseFunctions;
import session.session;
import socket.packet.handlers.Listen_handler;
import socket.packet.handlers.senders.contact_handlers.PseudoToClient_handler;

/*
 * old style packet for request pseudo. Must be improved
 */
public class Req_pseudo_handler extends Listen_handler {

	public Req_pseudo_handler(session sess, Object packet) 
	{
		if(Misc.isWrongType(packet, new String("")))
			return;
		
		data = packet.toString();
		PseudoToClient_handler pck = new PseudoToClient_handler(Integer.decode((String) data),
				DatabaseFunctions.getPseudoByUID(Integer.decode((String) data)));
		pck.Send(sess.getSocket());
	}


}
