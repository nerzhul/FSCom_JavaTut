package socket.packet.handlers.sends;

import socket.packet.handlers.send_handler;

public class AskContacts_handler extends send_handler {

	public AskContacts_handler()
	{
		opcode = 0x0B;
		data = "11101";
	}
}
