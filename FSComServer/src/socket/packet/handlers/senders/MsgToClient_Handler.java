package socket.packet.handlers.senders;

import socket.packet.handlers.send_handler;

public class MsgToClient_Handler extends send_handler {

	public MsgToClient_Handler(Integer uid, String msg) {
		opcode = 0x14;
		data = uid + "%$%" + msg;
	}

}
