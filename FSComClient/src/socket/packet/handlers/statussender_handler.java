package socket.packet.handlers;

import session.clientstatus;

public class statussender_handler extends send_handler {


	public statussender_handler(Integer status)
	{
		opcode = 0x09;
		data = status.toString();
	}
	public boolean HasValidData() {
		if(Integer.decode(data) > 0 && Integer.decode(data) < clientstatus.MAX_STATUS.getvalue())
			return true;
		else
			return false;
	}

}
