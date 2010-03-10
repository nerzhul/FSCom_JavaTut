package socket.packet.handlers.senders.contact_handlers;

import socket.packet.handlers.Send_handler;
import socket.packet.objects.Message;

/*
 * message sent to client
 */
public class MsgToClient_Handler extends Send_handler {

	public MsgToClient_Handler(Integer uid, String msg) {
		opcode = 0x14;
		data = new Message(msg,uid);
	}
}
