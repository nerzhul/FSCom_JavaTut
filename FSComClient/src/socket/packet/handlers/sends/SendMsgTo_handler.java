package socket.packet.handlers.sends;

import session.objects.contact;
import socket.packet.handlers.send_handler;
import socket.packet.objects.message;

public class SendMsgTo_handler extends send_handler {

	public SendMsgTo_handler(String text, contact contact)
	{
		opcode = 0x13;
		data = new message(text,contact.getCid());
	}
}
