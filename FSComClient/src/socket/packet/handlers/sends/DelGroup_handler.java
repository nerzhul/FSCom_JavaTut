package socket.packet.handlers.sends;

import socket.packet.handlers.Send_handler;

public class DelGroup_handler extends Send_handler 
{
	public DelGroup_handler(Integer gid)
	{
		opcode = 0x27;
		data = gid;
	}
}
