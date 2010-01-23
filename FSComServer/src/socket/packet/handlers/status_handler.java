package socket.packet.handlers;

import java.sql.SQLException;

import session.session;

public class status_handler extends send_handler {

	public status_handler(session sess) throws SQLException
	{
		opcode = 0x0A;
		data = "01000";
		sess.SetStatus(Integer.decode(data.substring(0,1)));
		if(Integer.decode(data.substring(2)) == 1)
			sess.connect_client();

	}
}
