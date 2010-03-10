package socket.packet.handlers.senders.contact_handlers;

import socket.packet.handlers.Send_handler;

/*
 * confirm contact was deleted
 */
public class ContactDeleted_handler extends Send_handler {

	public ContactDeleted_handler(Integer _uid)
	{
		opcode = 0x1D;
		data = _uid;
	}
}
