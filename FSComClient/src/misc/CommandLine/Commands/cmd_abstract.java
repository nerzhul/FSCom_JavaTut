package misc.CommandLine.Commands;

import socket.packet.handlers.Abstract_handler;
import socket.packet.handlers.Send_handler;

public class cmd_abstract {

	Abstract_handler handler;
	//on envoi le packet
	public void Send()
	{
		((Send_handler) handler).Send();
	}

}
