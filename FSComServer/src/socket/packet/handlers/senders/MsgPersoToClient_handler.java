package socket.packet.handlers.senders;

import socket.packet.handlers.send_handler;
import socket.packet.objects.IdAndData;

public class MsgPersoToClient_handler extends send_handler {

	public MsgPersoToClient_handler(Integer uid, String pmsg) {
		opcode = 0x19;
		data = new IdAndData(uid,pmsg);
	}

}
