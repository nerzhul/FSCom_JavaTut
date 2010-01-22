package socket.packet.handlers;

public class cont_disconct_handler extends send_handler {

	public cont_disconct_handler(Integer uid)
	{
		opcode = 0x0D;
		data = uid.toString();
	}
}
