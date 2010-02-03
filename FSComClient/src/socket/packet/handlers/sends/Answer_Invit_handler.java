package socket.packet.handlers.sends;

import socket.packet.handlers.send_handler;

public class Answer_Invit_handler extends send_handler {

	public Answer_Invit_handler(Integer uid, Integer dat)
	{
		opcode = 0x1F;
		data = uid + "//////.//////" + dat;
	}
}
