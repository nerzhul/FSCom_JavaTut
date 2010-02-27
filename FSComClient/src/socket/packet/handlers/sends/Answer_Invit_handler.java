package socket.packet.handlers.sends;

import socket.packet.handlers.Send_handler;
import socket.packet.objects.IdAndData;

public class Answer_Invit_handler extends Send_handler {

	public Answer_Invit_handler(Integer uid, Integer dat)
	{
		opcode = 0x1F;
		data = new IdAndData(uid,dat.toString());
	}
}
