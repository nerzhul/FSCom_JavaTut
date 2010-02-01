package misc.CommandLine.Commands;

import socket.packet.handlers.sends.connect_handler;

public class cmd_connect extends cmd_abstract {

	public cmd_connect(String args)
	{
		handler = new connect_handler(args);
	}
}
