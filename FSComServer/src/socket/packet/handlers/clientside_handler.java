package socket.packet.handlers;

import java.io.IOException;
import misc.Log;

public class clientside_handler extends listen_handler{

	public clientside_handler(Integer error) throws IOException
	{
		opcode = error;
		PrintError();
	}
	
	void PrintError()
	{
		Log.outError("Received Packet opcode : 0x" + Integer.toHexString(opcode)
				+ " and station is not a client !");
	}
}
