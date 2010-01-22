package socket.packet.handlers;

import java.sql.SQLException;

import session.session;

public class status_handler extends send_handler {

	public status_handler(session sess) throws SQLException
	{
		opcode = 0x0A;
		data = "01000";
		sess.SetStatus(Integer.decode(data));
		sess.connect_client();
	}
}
