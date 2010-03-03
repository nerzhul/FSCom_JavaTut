package socket.packet.handlers.senders.contact_handlers;

import socket.packet.handlers.Send_handler;
import socket.packet.objects.IdAndData;

public class IPToClient_handler extends Send_handler {

	public IPToClient_handler(Integer _uid, String ip)
	{
		opcode = 0x30;
		data = new IdAndData(_uid,ip);
	}
}