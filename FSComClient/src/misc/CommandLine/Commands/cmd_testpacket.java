package misc.CommandLine.Commands;

import socket.packet.handlers.sends.TestPacket_handler;
public class cmd_testpacket  extends cmd_abstract{

	//on test le packet
	public cmd_testpacket(String args)
	{
		handler = new TestPacket_handler(args);
	}
	
	
}
