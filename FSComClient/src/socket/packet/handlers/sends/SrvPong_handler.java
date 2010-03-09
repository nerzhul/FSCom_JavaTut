package socket.packet.handlers.sends;

import socket.packet.handlers.Send_handler;

public class SrvPong_handler extends Send_handler {

	public SrvPong_handler()
	{
		opcode = 0x09;
		data = "3";
	}

	public boolean HasValidData() {

		if(data.equals("3"))
			return true;
		else
			return false;
	}
}
