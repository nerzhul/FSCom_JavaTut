package socket.packet.handlers;

public class cont_connected_handler extends send_handler {

	public cont_connected_handler(String name, String status, String pp, Integer uid)
	{
		opcode = 0x10;
		data = name + "%" + status + "%" + pp + "%" + uid;
	}
}
