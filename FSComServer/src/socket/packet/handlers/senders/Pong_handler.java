package socket.packet.handlers.senders;

import socket.packet.handlers.Send_handler;

/*
 * ping response
 */
public class Pong_handler extends Send_handler{

	public Pong_handler()
	{
		opcode = 0x01;
		data = "1";
	}
}
