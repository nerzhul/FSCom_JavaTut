package socket.packet.handlers.senders;

import socket.packet.handlers.Send_handler;

public class Cont_Connected_handler extends Send_handler {

	public Cont_Connected_handler(String name, String status, String pp, Integer uid)
	{
		opcode = 0x10;
		data = name + "%" + status + "%" + pp + "%" + uid;
	}
}
