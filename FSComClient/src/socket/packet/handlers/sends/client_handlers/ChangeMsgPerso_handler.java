package socket.packet.handlers.sends.client_handlers;

import socket.packet.handlers.Send_handler;

public class ChangeMsgPerso_handler extends Send_handler {

	public ChangeMsgPerso_handler(String str)
	{
		opcode = 0x18;
		data = str;
	}
}
