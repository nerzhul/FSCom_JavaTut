package socket.packet.handlers.sends.group_handlers;

import socket.packet.handlers.Send_handler;
import socket.packet.objects.IdAndData;

public class RenameGroup_handler extends Send_handler 
{
	public RenameGroup_handler(Integer gid, String nName)
	{
		opcode = 0x29;
		data = new IdAndData(gid,nName);
	}
}
