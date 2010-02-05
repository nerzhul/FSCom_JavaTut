package socket.packet.handlers.listened;

import misc.Log;
import socket.packet.handlers.listen_handler;

public class depreciated_handler extends listen_handler {

	public depreciated_handler(Integer error)
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
