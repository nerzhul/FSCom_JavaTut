package misc.CommandLine.Commands;

import java.io.IOException;

import socket.sender;

public class cmd_ping {

	public cmd_ping() throws IOException
	{
		sender.SendPacket(0x00,"0");
	}
}
