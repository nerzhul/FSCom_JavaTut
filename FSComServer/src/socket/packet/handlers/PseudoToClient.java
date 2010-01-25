package socket.packet.handlers;

public class PseudoToClient extends send_handler {

	public PseudoToClient(Integer uid, String name) {
		opcode = 0x17;
		data = uid + "{}--{}" + name;
	}

}
