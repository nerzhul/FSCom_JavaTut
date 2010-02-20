package socket.packet.handlers.sends;

import socket.packet.handlers.Send_handler;

public class DelContact_handler extends Send_handler {

	public DelContact_handler(Integer cid) {
		opcode = 0x1C; 
		data = cid + "@[]@" + "1";
	}

}
