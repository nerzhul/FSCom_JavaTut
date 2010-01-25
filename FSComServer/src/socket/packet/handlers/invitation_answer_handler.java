package socket.packet.handlers;

import misc.Log;
import session.session;

public class invitation_answer_handler extends listen_handler {

	public invitation_answer_handler(session sess, Object packet) 
	{
		String pck[] = packet.toString().split("{]}#");
		if(pck.length != 2)
			Log.outError("Received invalid invitation_answer packet");
		else
			sess.ManageInvitation(Integer.decode(pck[0]),Integer.decode(pck[1]));
	}

}
