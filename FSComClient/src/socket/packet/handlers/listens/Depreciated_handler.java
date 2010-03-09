package socket.packet.handlers.listens;

import misc.Log;
import socket.packet.handlers.Listen_handler;

public class Depreciated_handler extends Listen_handler {

	public Depreciated_handler(Integer error)
	{
		opcode = error;
		PrintError();
	}
	
	protected void PrintError()
	{
		Log.outError("Received Packet opcode : 0x" + Integer.toHexString(opcode)
				+ " was declared depreciated !");
	}
}
