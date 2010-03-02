package socket.packet.handlers.senders;

import socket.packet.handlers.Send_handler;
import socket.packet.objects.IdAndData;

public class BlockContact_handler extends Send_handler {

	public BlockContact_handler(Integer uid,Integer res) {
		opcode = 0x12;
		data = new IdAndData(uid,res.toString());
	}


}
