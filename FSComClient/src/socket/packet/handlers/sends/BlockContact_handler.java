package socket.packet.handlers.sends;

import socket.packet.handlers.Send_handler;
import socket.packet.objects.IdAndData;

public class BlockContact_handler extends Send_handler {

	public BlockContact_handler(Integer _uid, Integer method)
	{
		opcode = 0x11;
		data = new IdAndData(_uid,method.toString());
	}
}
