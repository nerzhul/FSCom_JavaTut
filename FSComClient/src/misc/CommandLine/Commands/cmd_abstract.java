package misc.CommandLine.Commands;

import socket.packet.handlers.Abstract_handler;
import socket.packet.handlers.Send_handler;

public class cmd_abstract {

	Abstract_handler handler;
	
	public void Send()
	{
		((Send_handler) handler).Send();
	}
	
	public boolean HasValidData()
	{
		if(handler != null && handler.HasValidData())
			return true;
		
		return false;
		
	}
}
