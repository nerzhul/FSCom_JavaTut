package socket.packet.handlers.senders.contact_handlers;

import socket.packet.handlers.Send_handler;
import socket.packet.objects.IdAndData;

public class StatusToClient_Handler extends Send_handler {

	public StatusToClient_Handler(Integer uid, Integer status) {
		opcode = 0x15;
		data = new IdAndData(uid,status.toString());
	}

	
}
