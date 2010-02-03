package socket.packet.handlers.sends;

import socket.packet.handlers.send_handler;

public class DelContact_handler extends send_handler {

	public DelContact_handler(Integer cid) {
		opcode = 0x1C; 
		data = cid + "@[]@" + "1";
	}

}
