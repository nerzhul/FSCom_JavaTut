package socket.packet.handlers.senders;

import misc.Log;
import session.session;
import socket.packet.handlers.Listen_handler;

public class Invitation_Answer_handler extends Listen_handler {

	public Invitation_Answer_handler(session sess, Object packet) 
	{
		String pck[] = packet.toString().split("//////.//////");
		if(pck.length != 2)
			Log.outError("Received invalid invitation_answer packet");
		else
			sess.ManageInvitation(Integer.decode(pck[0]),Integer.decode(pck[1]));
	}

}
