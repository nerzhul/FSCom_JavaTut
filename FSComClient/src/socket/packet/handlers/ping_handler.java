package socket.packet.handlers;

public class ping_handler extends send_handler {

	public ping_handler()
	{
		opcode = 0x00;
		data = "0";
	}
	void PrintError() {}

	public boolean HasValidData()
	{
		if(data.equals("0"))
			return true;
		else
			return false;
	}
}
