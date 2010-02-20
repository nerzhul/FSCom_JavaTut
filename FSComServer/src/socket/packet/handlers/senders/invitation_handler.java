package socket.packet.handlers.senders;

import socket.packet.handlers.Send_handler;
import database.DatabaseFunctions;

public class Invitation_handler extends Send_handler {

	public Invitation_handler(Integer uid) {
		opcode = 0x1E;
		data = uid + "[][]" + DatabaseFunctions.getAccountNameByUID(uid);
	}

}
