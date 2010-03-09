package socket.packet.handlers.senders;

import socket.packet.handlers.Send_handler;
import socket.packet.objects.Avatar;

public class AvatarAnswer_handler extends Send_handler {

	public AvatarAnswer_handler(Avatar av)
	{
		opcode = 0x32;
		data = av;
	}
}
