package socket.packet.handlers.senders.group_handlers;

import socket.packet.handlers.Send_handler;
import socket.packet.objects.IdAndData;

/*
 * confirm datas for adding group
 */
public class AddGroup_handler extends Send_handler {

	public AddGroup_handler(Integer gid, String name)
	{
		opcode = 0x25;
		data = new IdAndData(gid,name);
	}
}
