package socket.packet.handlers.senders;

import socket.packet.handlers.Send_handler;

/*
 * Result of account creation
 */
public class AccCreate_handler extends Send_handler {

	public AccCreate_handler(Integer res)
	{
		opcode = 0x2E;
		data = res.toString();
	}
}
