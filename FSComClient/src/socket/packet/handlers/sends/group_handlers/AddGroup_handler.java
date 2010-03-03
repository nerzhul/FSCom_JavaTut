package socket.packet.handlers.sends.group_handlers;

import socket.packet.handlers.Send_handler;
import socket.packet.objects.IdAndData;

public class AddGroup_handler extends Send_handler 
{
	public AddGroup_handler(Integer gid, String name)
	{
		opcode = 0x25;
		data = new IdAndData(gid,name);
	}
}
