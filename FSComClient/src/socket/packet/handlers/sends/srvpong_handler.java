package socket.packet.handlers.sends;

import socket.packet.handlers.send_handler;

public class srvpong_handler extends send_handler {

	public srvpong_handler()
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
