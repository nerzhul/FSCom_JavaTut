package socket.packet.handlers;

import java.io.IOException;
import misc.Log;

/*
 * for packet which aren't handled
 */
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
