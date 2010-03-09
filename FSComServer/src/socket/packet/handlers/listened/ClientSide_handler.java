package socket.packet.handlers.listened;

import java.io.IOException;

import socket.packet.handlers.Listen_handler;

import misc.Log;

public class ClientSide_handler extends Listen_handler{

	public ClientSide_handler(Integer error) throws IOException
	{
		opcode = error;
		PrintError();
	}
	
	protected void PrintError()
	{
		Log.outError("Received Packet opcode : 0x" + Integer.toHexString(opcode)
				+ " and station is not a client !");
	}
}
