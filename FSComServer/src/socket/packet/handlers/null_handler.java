package socket.packet.handlers;

import java.io.IOException;
import misc.Log;

public class null_handler extends abstract_handler{


	public null_handler(Integer error) throws IOException
	{
		opcode = error;
		PrintError();
	}
	
	void PrintError() throws IOException
	{
		Log.outError("Unhandled Packet opcode : 0x" + Integer.toHexString(opcode));
	}
}
