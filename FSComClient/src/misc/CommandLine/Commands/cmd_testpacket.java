package misc.CommandLine.Commands;

import socket.packet.handlers.testpacket_handler;
public class cmd_testpacket  extends cmd_abstract{

	public cmd_testpacket(String args)
	{
		handler = new testpacket_handler(args);
	}
	
	
}
