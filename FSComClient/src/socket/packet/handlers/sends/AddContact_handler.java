package socket.packet.handlers.sends;

import socket.packet.handlers.Send_handler;

public class AddContact_handler extends Send_handler {

	public AddContact_handler(String name)
	{
		opcode = 0x1A;
		data = name;
	}
}
