package socket.packet.handlers.senders.group_handlers;

import socket.packet.handlers.Send_handler;

public class ConfirmGroupAdded_handler extends Send_handler {

	public ConfirmGroupAdded_handler(Integer gid)
	{
		opcode = 0x26;
		data = gid;
	}
}
