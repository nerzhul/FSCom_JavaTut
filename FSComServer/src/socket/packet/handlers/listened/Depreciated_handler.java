package socket.packet.handlers.listened;

import misc.Log;
import socket.packet.handlers.Listen_handler;

/*
 * listen handler for depreciated packets
 */
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
