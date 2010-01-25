package socket.packet.handlers;

public class StatusToClient_Handler extends send_handler {

	public StatusToClient_Handler(Integer uid, Integer status) {
		opcode = 0x15;
		data = uid + "%%$%%" + status;
	}

	
}
