package socket.packet.handlers.sends.client_handlers;

import socket.packet.handlers.Send_handler;

public class StatusSender_handler extends Send_handler {


	public StatusSender_handler(Integer status)
	{
		opcode = 0x09;
		data = status;
	}
}
