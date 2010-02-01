package socket.packet.handlers.listens;

import session.events;
import socket.packet.handlers.listen_handler;
import thread.threading;

public class srvconnect_handler extends listen_handler {

	public srvconnect_handler(String args)
	{
		data = args;
	}

	public boolean HasValidData() 
	{
		if(data.equals("2"))
			return true;
		else
		{
			threading.StopSender();
			if(data.equals("1"))
				events.ConnectionError();
			else if(data.equals("3"))
				events.BadLoginInformations();

			return false;
		}
			
	}
}
