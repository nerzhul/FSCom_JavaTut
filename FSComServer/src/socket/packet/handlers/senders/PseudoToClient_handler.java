package socket.packet.handlers.senders;

import socket.packet.handlers.Send_handler;
import socket.packet.objects.IdAndData;

public class PseudoToClient_handler extends Send_handler {

	public PseudoToClient_handler(Integer uid, String name) {
		opcode = 0x17;
		data = new IdAndData(uid,name);
	}

}
