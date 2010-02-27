package socket.packet.p2pobjects;

import socket.packet.handlers.Sendp2p_handler;

public class Avatar_packet extends Sendp2p_handler
{
	public Avatar_packet(Object img)
	{
		opcode = 0x2C;
		data = img;
	}
}
