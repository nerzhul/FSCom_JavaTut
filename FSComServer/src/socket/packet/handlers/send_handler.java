package socket.packet.handlers;

import java.net.Socket;

import session.session;
import socket.sender;

public class send_handler extends abstract_handler {

	protected session m_sess;
	void PrintError(){}
	public void Send(Socket sock)
	{
		sender snd = new sender(sock, opcode, data);
		snd.SendPacket();
	}

}
