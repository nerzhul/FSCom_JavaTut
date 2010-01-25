package socket.packet.handlers.sends;

import socket.packet.handlers.send_handler;

public class AskGroups_handler extends send_handler {

	public AskGroups_handler()
	{
		opcode = 0x0E;
		data = "001457";
	}
}
