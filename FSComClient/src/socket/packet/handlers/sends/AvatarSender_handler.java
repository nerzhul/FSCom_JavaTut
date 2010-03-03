package socket.packet.handlers.sends;

import javax.swing.ImageIcon;

import socket.packet.handlers.Send_handler;
import socket.packet.objects.Avatar;

public class AvatarSender_handler extends Send_handler {

	public AvatarSender_handler(ImageIcon i)
	{
		opcode = 0x33;
		data = new Avatar(0,i);
	}
}
