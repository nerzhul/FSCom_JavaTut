package socket.packet.handlers;

import java.sql.SQLException;

import session.session;

public class status_handler extends send_handler {

	public status_handler(session sess, Object packet) throws SQLException
	{
		opcode = 0x0A;
		data = "01000";
		sess.SetStatus(Integer.decode(packet.toString().substring(0,1)));
		if(Integer.decode(packet.toString().substring(2)) == 1)
			sess.connect_client();
		else
			sess.broadcast_SomethingChanged(1);

	}
}
