package socket.packet.handlers.senders;

import socket.packet.handlers.send_handler;
import socket.packet.objects.IdAndData;

public class PseudoToClient_handler extends send_handler {

	public PseudoToClient_handler(Integer uid, String name) {
		opcode = 0x17;
		data = new IdAndData(uid,name);
	}

}
