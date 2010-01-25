package socket.packet.handlers.sends;

import socket.packet.handlers.send_handler;

public class AskStatus_handler extends send_handler {

	public AskStatus_handler()
	{
		opcode = 0x0B;
		data = "11101";
	}
	
	public boolean HasValidData() {
		// TODO Auto-generated method stub
		return false;
	}

}
