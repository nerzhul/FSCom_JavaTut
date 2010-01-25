package socket.packet.handlers.senders;

import socket.packet.handlers.send_handler;

public class pong_handler extends send_handler{

	public pong_handler()
	{
		opcode = 0x01;
		data = "1";
	}
}
