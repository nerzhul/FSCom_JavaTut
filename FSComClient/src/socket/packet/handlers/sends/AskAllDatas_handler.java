package socket.packet.handlers.sends;

import socket.packet.handlers.Send_handler;
public class AskAllDatas_handler extends Send_handler {

	public AskAllDatas_handler(Integer st)
	{
		opcode = 0x21;
		data = st;
	}
}
