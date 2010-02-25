package socket.packet.handlers.sends;

import socket.packet.handlers.Send_handler;
import socket.packet.objects.IdAndData;

public class MoveGroup_handler extends Send_handler {

	public MoveGroup_handler(Integer uid, Integer gid)
	{
		opcode = 0x24;
		data = new IdAndData(uid,gid.toString()); 
	}
}
