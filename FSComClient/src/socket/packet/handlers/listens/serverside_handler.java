package socket.packet.handlers.listens;

import socket.packet.handlers.listen_handler;
import misc.Log;

public class serverside_handler extends listen_handler {

	public serverside_handler(Integer error)
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
