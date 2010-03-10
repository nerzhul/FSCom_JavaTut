package socket.packet.handlers.senders.group_handlers;

import socket.packet.handlers.Send_handler;
import socket.packet.objects.IdAndData;

/*
 * real confirm for adding group
 */
public class ConfirmGroupAdded_handler extends Send_handler {

	public ConfirmGroupAdded_handler(IdAndData pack)
	{
		opcode = 0x26;
		data = pack;
	}
}
