package socket.packet.handlers.senders;

import socket.packet.handlers.Send_handler;

public class StatusToClient_Handler extends Send_handler {

	public StatusToClient_Handler(Integer uid, Integer status) {
		opcode = 0x15;
		data = uid + "%%$%%" + status;
	}

	
}
