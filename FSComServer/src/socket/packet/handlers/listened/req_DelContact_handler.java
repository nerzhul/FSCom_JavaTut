package socket.packet.handlers.listened;

import misc.Log;
import session.session;
import socket.packet.handlers.Listen_handler;

public class Req_DelContact_handler extends Listen_handler {

	public Req_DelContact_handler(session sess, Object packet) 
	{
		String pck[] = packet.toString().split("@[]@");
		if(pck.length == 2)
			sess.DelContact(Integer.decode(pck[0]),Integer.decode(pck[1]));
		else
			Log.outError("Malformed delete contact packet recv !");

	}

}
