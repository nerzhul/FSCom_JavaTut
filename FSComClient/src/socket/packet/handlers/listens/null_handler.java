package socket.packet.handlers.listens;

import socket.packet.handlers.listen_handler;
import misc.Log;

public class null_handler extends listen_handler{

	public null_handler(Integer error)
	{
		opcode = error;
		PrintError();
	}
	
	protected void PrintError()
	{
		Log.outError("Unhandled Packet opcode : 0x" + Integer.toHexString(opcode));
	}
}
