package socket.packet.handlers.sends;

import socket.packet.handlers.send_handler;

public class ping_handler extends send_handler {

	public ping_handler()
	{
		opcode = 0x00;
		data = "0";
	}

	public boolean HasValidData()
	{
		if(data.equals("0"))
			return true;
		else
			return false;
	}
}
