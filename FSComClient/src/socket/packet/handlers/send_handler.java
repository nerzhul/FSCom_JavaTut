package socket.packet.handlers;

import socket.sender;

public abstract class send_handler extends abstract_handler {

	void PrintError(){}
	public void Send() 
	{
		sender.SendPacket(opcode,data);
	}
	public boolean HasValidData() {
		return true;
	}

}
