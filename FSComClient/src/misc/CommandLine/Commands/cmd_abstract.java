package misc.CommandLine.Commands;

import socket.packet.handlers.abstract_handler;
import socket.packet.handlers.send_handler;

public class cmd_abstract {

	abstract_handler handler;
	
	public void Send()
	{
		((send_handler) handler).Send();
	}
	
	public boolean HasValidData()
	{
		if(handler != null && handler.HasValidData())
			return true;
		
		return false;
		
	}
}
