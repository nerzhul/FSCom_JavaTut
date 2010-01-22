package misc.CommandLine.Commands;

import socket.packet.handlers.ping_handler;

public class cmd_ping extends cmd_abstract{

	public cmd_ping()
	{
		handler = new ping_handler();
	}
}
