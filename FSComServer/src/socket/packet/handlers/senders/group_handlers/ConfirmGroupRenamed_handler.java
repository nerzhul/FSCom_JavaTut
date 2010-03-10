package socket.packet.handlers.senders.group_handlers;

import socket.packet.handlers.Send_handler;
import socket.packet.objects.IdAndData;

/*
 * confirm group was renamed
 */
public class ConfirmGroupRenamed_handler extends Send_handler {

	public ConfirmGroupRenamed_handler(Integer gid, String name)
	{
		opcode = 0x2A;
		data = new IdAndData(gid,name);
	}
}
