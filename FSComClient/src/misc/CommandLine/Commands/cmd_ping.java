package misc.CommandLine.Commands;

import socket.packet.handlers.sends.Ping_handler;

public class cmd_ping extends cmd_abstract{

	//fonction de ping
	public cmd_ping()
	{
		handler = new Ping_handler();
	}
}
