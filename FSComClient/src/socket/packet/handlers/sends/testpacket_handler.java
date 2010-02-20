package socket.packet.handlers.sends;

import socket.packet.handlers.Send_handler;

public class TestPacket_handler extends Send_handler{

	public TestPacket_handler(String args)
	{
		opcode = 0xFF;
		data = args;
	}
	
	public boolean HasValidData()
	{
		if(data != null && !data.equals("") && !data.equals(" "))
			return true;
		else
			return false;
	}
}
