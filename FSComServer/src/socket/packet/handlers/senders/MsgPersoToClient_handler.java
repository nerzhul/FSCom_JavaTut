package socket.packet.handlers.senders;

import socket.packet.handlers.Send_handler;
import socket.packet.objects.IdAndData;

public class MsgPersoToClient_handler extends Send_handler {

	public MsgPersoToClient_handler(Integer uid, String pmsg) {
		opcode = 0x19;
		data = new IdAndData(uid,pmsg);
	}

}
