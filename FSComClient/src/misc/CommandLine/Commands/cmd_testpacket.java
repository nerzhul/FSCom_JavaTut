package misc.CommandLine.Commands;

import java.io.IOException;

import socket.sender;
public class cmd_testpacket {

	public cmd_testpacket(String args) throws IOException	
	{
		sender.SendPacket(0xFF,args);
	}
	
	
}
