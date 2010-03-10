package socket.packet.handlers;

import java.net.Socket;

import session.session;
import socket.Sender;

/*
 *	used on every packet to send 
 */
public class Send_handler extends Abstract_handler {

	protected session m_sess;
	void PrintError(){}
	public void Send(Socket sock)
	{
		Sender snd = new Sender(sock, opcode, data,m_sess);
		snd.SendPacket();
	}

}
