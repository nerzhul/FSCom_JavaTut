package socket.packet.handlers.sends;

import socket.packet.handlers.Send_handler;
import socket.packet.objects.IdAndData;

public class DelContact_handler extends Send_handler {

	public DelContact_handler(Integer cid) {
		opcode = 0x1C; 
		data = new IdAndData(cid,"1");
	}

}
