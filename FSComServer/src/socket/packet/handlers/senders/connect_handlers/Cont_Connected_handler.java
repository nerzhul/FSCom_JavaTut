package socket.packet.handlers.senders.connect_handlers;

import socket.packet.ConnectData;
import socket.packet.handlers.Send_handler;

public class Cont_Connected_handler extends Send_handler {

	public Cont_Connected_handler(String name, Integer status, String pp, Integer uid, String pseudo)
	{
		opcode = 0x10;
		data = new ConnectData(name,status,pp,uid,pseudo);
	}
}
