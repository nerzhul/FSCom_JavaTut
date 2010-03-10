package socket.packet.handlers.senders.connect_handlers;

import socket.packet.handlers.Send_handler;

/*
 * contact uid when disconnect
 */
public class Cont_Disconct_handler extends Send_handler {

	public Cont_Disconct_handler(Integer uid)
	{
		opcode = 0x0D;
		data = uid;
	}
}
