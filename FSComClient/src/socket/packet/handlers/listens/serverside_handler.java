package socket.packet.handlers.listens;

import socket.packet.handlers.Listen_handler;
import misc.Log;

public class ServerSide_handler extends Listen_handler {

	public ServerSide_handler(Integer error)
	{
		opcode = error;
		PrintError();
	}
	
	protected void PrintError()
	{
		Log.outError("Received Packet opcode : 0x" + Integer.toHexString(opcode)
				+ " and station is not a server !");
	}
}
