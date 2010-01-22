package socket.packet.handlers;

public class connect_handler extends send_handler {

	void PrintError() {}

	public connect_handler(String args)
	{
		opcode = 0x04;
		data = Reparse(args);
	}
	
	private String Reparse(String args)
	{
		String parse[] = args.trim().split(" ");
		
		if(parse.length != 2)
			return null;
		else
		{
			String tmpdata = "0" + parse[0] + "0/0" + parse[1] + "0";
			return tmpdata;
		}
	}

	public boolean HasValidData() 
	{
		if(data == null)
			return false;
		else
			return true;
	}
}
