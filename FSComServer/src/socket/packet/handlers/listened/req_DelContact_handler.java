package socket.packet.handlers.listened;

import misc.Log;
import session.session;
import socket.packet.handlers.listen_handler;

public class req_DelContact_handler extends listen_handler {

	public req_DelContact_handler(session sess, Object packet) 
	{
		String pck[] = packet.toString().split("@[]@");
		if(pck.length == 2)
			sess.DelContact(Integer.decode(pck[0]),Integer.decode(pck[1]));
		else
			Log.outError("Malformed delete contact packet recv !");

	}

}
