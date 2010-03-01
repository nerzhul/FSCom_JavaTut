package socket.packet.handlers.p2psends;

import socket.packet.handlers.Sendp2p_handler;
import socket.packet.p2pobjects.Avatar;

public class Avatar_packet extends Sendp2p_handler
{
	public Avatar_packet(Avatar img)
	{
		opcode = 0x2C;
		data = img;
	}
}
