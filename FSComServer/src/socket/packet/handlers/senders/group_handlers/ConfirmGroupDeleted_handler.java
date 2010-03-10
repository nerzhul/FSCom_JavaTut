package socket.packet.handlers.senders.group_handlers;

import socket.packet.handlers.Send_handler;

/*
 * confirm group was deleted
 */
public class ConfirmGroupDeleted_handler extends Send_handler 
{
	public ConfirmGroupDeleted_handler(Integer gid)
	{
		opcode = 0x28;
		data = gid;
	}
}
