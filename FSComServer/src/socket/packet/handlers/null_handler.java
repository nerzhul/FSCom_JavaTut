package socket.packet.handlers;

import java.io.IOException;
import misc.Log;

public class Null_handler extends Abstract_handler{


	public Null_handler(Integer error) throws IOException
	{
		opcode = error;
		PrintError();
	}
	
	void PrintError() throws IOException
	{
		Log.outError("Unhandled Packet opcode : 0x" + Integer.toHexString(opcode));
	}
}
