package socket.packet.handlers;

import misc.Log;

public class null_handler extends listen_handler{

	public null_handler(Integer error)
	{
		opcode = error;
		PrintError();
	}
	
	void PrintError()
	{
		Log.outError("Unhandled Packet opcode : 0x" + Integer.toHexString(opcode));
	}
}
