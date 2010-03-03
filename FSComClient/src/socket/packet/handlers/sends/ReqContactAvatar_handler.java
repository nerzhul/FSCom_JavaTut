package socket.packet.handlers.sends;

import socket.packet.handlers.Send_handler;
import socket.packet.objects.IdAndData;

public class ReqContactAvatar_handler extends Send_handler {

	public ReqContactAvatar_handler(Integer _uid)
	{
		opcode = 0x31;
		data = new IdAndData(_uid,"1");
	}
}
