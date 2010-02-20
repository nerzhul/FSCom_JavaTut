package socket.packet.handlers.sends;

import socket.packet.handlers.send_handler;
import socket.packet.objects.IdAndData;

public class MoveGroup_handler extends send_handler {

	public MoveGroup_handler(Integer uid, Integer gid)
	{
		opcode = 0x24;
		data = new IdAndData(uid,gid.toString()); 
	}
}
