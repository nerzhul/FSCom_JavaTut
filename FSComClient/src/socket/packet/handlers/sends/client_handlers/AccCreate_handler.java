package socket.packet.handlers.sends.client_handlers;

import socket.packet.handlers.Send_handler;
import socket.packet.objects.AccCreateDatas;

public class AccCreate_handler extends Send_handler {

	public AccCreate_handler(String name, String pwd)
	{
		opcode = 0x2D;
		data = new AccCreateDatas(name,pwd);
	}
}
