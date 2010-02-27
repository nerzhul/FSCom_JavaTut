package socket.packet.handlers.listens;

import socket.packet.handlers.Listen_handler;
import misc.Log;

public class Null_handler extends Listen_handler{

	public Null_handler(Integer error)
	{
		opcode = error;
		PrintError();
	}
	
	protected void PrintError()
	{
		Log.outError("Unhandled Packet opcode : 0x" + Integer.toHexString(opcode));
	}
}
