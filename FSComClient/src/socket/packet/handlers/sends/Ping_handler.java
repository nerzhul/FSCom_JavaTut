package socket.packet.handlers.sends;

import socket.packet.handlers.Send_handler;

public class Ping_handler extends Send_handler {

	public Ping_handler()
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
