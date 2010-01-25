package socket.packet.handlers.senders;

import socket.packet.handlers.send_handler;
import database.DatabaseFunctions;

public class invitation_handler extends send_handler {

	public invitation_handler(Integer uid) {
		opcode = 0x1E;
		data = uid + "[][]" + DatabaseFunctions.getAccountNameByUID(uid);
	}

}
