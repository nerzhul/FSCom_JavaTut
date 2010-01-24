package socket.packet.handlers;

public class MsgToClient_Handler extends send_handler {

	public MsgToClient_Handler(Integer uid, String msg) {
		opcode = 0x14;
		data = uid + "%$%" + msg;
	}

}
