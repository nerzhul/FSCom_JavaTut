package socket.packet.handlers.senders.contact_handlers;

import socket.packet.handlers.Send_handler;
import socket.packet.objects.IdAndData;

/*
 * pseudo requested to client
 */
public class PseudoToClient_handler extends Send_handler {

	public PseudoToClient_handler(Integer uid, String name) {
		opcode = 0x17;
		data = new IdAndData(uid,name);
	}
}
