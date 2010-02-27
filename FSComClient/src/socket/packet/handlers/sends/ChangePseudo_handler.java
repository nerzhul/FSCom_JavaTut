package socket.packet.handlers.sends;

import socket.packet.handlers.Send_handler;

public class ChangePseudo_handler extends Send_handler {

	public ChangePseudo_handler(String str)
	{
		opcode = 0x16;
		data = str;
	}
}
