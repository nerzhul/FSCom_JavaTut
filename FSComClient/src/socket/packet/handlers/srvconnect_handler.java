package socket.packet.handlers;

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
			return false;
	}
}
