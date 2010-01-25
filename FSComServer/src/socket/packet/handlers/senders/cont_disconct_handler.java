package socket.packet.handlers.senders;

import socket.packet.handlers.send_handler;

public class cont_disconct_handler extends send_handler {

	public cont_disconct_handler(Integer uid)
	{
		opcode = 0x0D;
		data = uid.toString();
	}
}
