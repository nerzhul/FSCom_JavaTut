package socket.packet.handlers.senders;

import misc.Log;
import session.session;
import socket.packet.handlers.Send_handler;

public class BlockContact_handler extends Send_handler {

	public BlockContact_handler(session sess, Object packet) {
		opcode = 0x12;
		data = packet.toString();
		m_sess = sess;
		Declare_Blocked(packet.toString());
	}

	void Declare_Blocked(String args)
	{
		String splitpacket[] = args.split("%");
		if(splitpacket.length == 2)
		{
			m_sess.block_contact(splitpacket[0],splitpacket[1]);
		}
		else
			Log.outError("blockcontact_handler : recv invalid packet");
	}
}
