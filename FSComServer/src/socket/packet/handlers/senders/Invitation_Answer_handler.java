package socket.packet.handlers.senders;

import session.session;
import socket.packet.handlers.Listen_handler;
import socket.packet.objects.IdAndData;

public class Invitation_Answer_handler extends Listen_handler {

	public Invitation_Answer_handler(session sess, Object packet) 
	{
		if(!packet.getClass().equals((new IdAndData(0,"")).getClass()))
			return;

		IdAndData pck = (IdAndData)packet;
		sess.ManageInvitation(pck.getUid(),Integer.decode(pck.getDat()));
	}

}
