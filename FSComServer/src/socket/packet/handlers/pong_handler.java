package socket.packet.handlers;

public class pong_handler extends send_handler{

	public pong_handler()
	{
		opcode = 0x01;
		data = "1";
	}
	void PrintError(){}

	

}
