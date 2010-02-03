package socket.packet.handlers.senders;

import socket.packet.handlers.send_handler;
import socket.packet.objects.message;

public class MsgToClient_Handler extends send_handler {

	public MsgToClient_Handler(Integer uid, String msg) {
		opcode = 0x14;
		data = new message(msg,uid);
	}

}
