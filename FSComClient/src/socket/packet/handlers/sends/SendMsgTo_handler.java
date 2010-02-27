package socket.packet.handlers.sends;

import session.contact;
import socket.packet.handlers.Send_handler;
import socket.packet.objects.Message;

public class SendMsgTo_handler extends Send_handler {

	public SendMsgTo_handler(String text, contact contact)
	{
		opcode = 0x13;
		data = new Message(text,contact.getCid());
	}
}
