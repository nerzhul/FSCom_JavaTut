package socket.packet.handlers;

public class PseudoToClient_handler extends send_handler {

	public PseudoToClient_handler(Integer uid, String name) {
		opcode = 0x17;
		data = uid + "{}--{}" + name;
	}

}
