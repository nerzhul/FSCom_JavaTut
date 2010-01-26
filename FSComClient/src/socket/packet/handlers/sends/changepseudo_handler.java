package socket.packet.handlers.sends;

import misc.Log;
import socket.packet.handlers.send_handler;

public class changepseudo_handler extends send_handler {

	public changepseudo_handler(String str)
	{
		Log.outError(str);
		opcode = 0x16;
		data = str;
	}
}
