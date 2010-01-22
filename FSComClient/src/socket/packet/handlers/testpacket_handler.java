package socket.packet.handlers;

public class testpacket_handler extends send_handler{

	public testpacket_handler(String args)
	{
		opcode = 0xFF;
		data = args;
	}
	
	public boolean HasValidData()
	{
		if(data != null && !data.equals("") && !data.equals(" "))
			return true;
		else
			return false;
	}
}
