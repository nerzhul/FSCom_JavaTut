package socket.packet.handlers.sends;

import session.defines.clientstatus;
import socket.packet.handlers.send_handler;

public class statussender_handler extends send_handler {


	public statussender_handler(Integer status)
	{
		opcode = 0x09;
		data = status;
	}
	public boolean HasValidData() {
		if(Integer.decode(data.toString().substring(0,1)) > 0 && Integer.decode(data.toString().substring(0,1)) < clientstatus.MAX_STATUS.getvalue())
			return true;
		else
			return false;
	}

}
