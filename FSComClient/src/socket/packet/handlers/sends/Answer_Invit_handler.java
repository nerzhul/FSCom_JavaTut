package socket.packet.handlers.sends;

import socket.packet.handlers.Send_handler;

public class Answer_Invit_handler extends Send_handler {

	public Answer_Invit_handler(Integer uid, Integer dat)
	{
		opcode = 0x1F;
		data = uid + "//////.//////" + dat;
	}
}
