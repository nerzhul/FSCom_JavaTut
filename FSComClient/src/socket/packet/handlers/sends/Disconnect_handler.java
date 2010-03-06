package socket.packet.handlers.sends;

import socket.packet.handlers.Send_handler;

public class Disconnect_handler extends Send_handler {

	public Disconnect_handler()
	{
		opcode = 0x05;
		data = 1;
	}
}
