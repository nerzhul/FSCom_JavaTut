package socket.packet.handlers;

import session.clientstatus;

public class statussender_handler extends send_handler {


	public statussender_handler(Integer status, boolean firstconnect)
	{
		opcode = 0x09;
		data = status.toString() + "%";
		if(firstconnect)
			data += "1";
		else
			data += "0";
	}
	public boolean HasValidData() {
		if(Integer.decode(data.substring(0,1)) > 0 && Integer.decode(data.substring(0,1)) < clientstatus.MAX_STATUS.getvalue())
			return true;
		else
			return false;
	}

}
