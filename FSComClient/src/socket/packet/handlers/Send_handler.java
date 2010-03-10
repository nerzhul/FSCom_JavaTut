package socket.packet.handlers;

import socket.Sender;

public abstract class Send_handler extends Abstract_handler {

	void PrintError(){}
	public void Send() 
	{
		Sender.SendPacket(opcode,data);
	}
}
