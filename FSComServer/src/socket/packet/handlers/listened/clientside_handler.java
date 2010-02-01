package socket.packet.handlers.listened;

import java.io.IOException;

import socket.packet.handlers.listen_handler;

import misc.Log;

public class clientside_handler extends listen_handler{

	public clientside_handler(Integer error) throws IOException
	{
		opcode = error;
		PrintError();
	}
	
	protected void PrintError()
	{
		Log.outError("Received Packet opcode : 0x" + Integer.toHexString(opcode)
				+ " and station is not a client !");
	}
}
