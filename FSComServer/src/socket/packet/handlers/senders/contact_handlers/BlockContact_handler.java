package socket.packet.handlers.senders.contact_handlers;

import socket.packet.handlers.Send_handler;
import socket.packet.objects.IdAndData;

/*
 * contact blocked with uid
 */
public class BlockContact_handler extends Send_handler {

	public BlockContact_handler(Integer uid,Integer res) {
		opcode = 0x12;
		data = new IdAndData(uid,res.toString());
	}
}
