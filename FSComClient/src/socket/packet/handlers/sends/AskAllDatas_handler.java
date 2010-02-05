package socket.packet.handlers.sends;

import socket.packet.handlers.send_handler;

public class AskAllDatas_handler extends send_handler {

	public AskAllDatas_handler(Integer st)
	{
		opcode = 0x21;
		data = st;
	}
}
