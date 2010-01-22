package socket.packet.handlers;

import java.net.Socket;

import socket.sender;

public class send_handler extends abstract_handler {

	void PrintError(){}
	public void Send(Socket sock)
	{
		sender snd = new sender(sock, opcode, data);
		snd.SendPacket();
	}

}
