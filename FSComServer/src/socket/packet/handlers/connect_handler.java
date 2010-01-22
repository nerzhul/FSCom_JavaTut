package socket.packet.handlers;

import java.sql.ResultSet;
import java.sql.SQLException;

import database.DatabaseTransactions;
import session.onconnect_answer;
import session.session;

public class connect_handler extends send_handler {

	private session m_sess;
	public connect_handler(Object args, session sess) throws SQLException
	{
		opcode = 0x08;
		m_sess = sess;
		data = VerifyLoginData(args);
	}
	
	private String VerifyLoginData(Object args) throws SQLException
	{
		String login = "", pass = "";
		String tmpstr[] = (args.toString()).split("0/0");
		if(tmpstr == null || tmpstr.length != 2)
			return ("" + onconnect_answer.CONN_PACKET_MALFORMED.getValue());

		login = tmpstr[0].substring(1);
		pass = tmpstr[1].substring(0,tmpstr[1].length() - 1);
		ResultSet query = DatabaseTransactions.DatabaseQuery(
				"select user,pass_hash from account where user = '" + login + "'");
		
		if(query != null && query.next())
		{
			do
			{
				if(query.getString("pass_hash").equals(pass))
				{
					m_sess.SetName(login);
					return ("" + onconnect_answer.CONN_SUCCESS.getValue());
				}					
				else
					return ("" + onconnect_answer.CONN_LOGIN_FAILED.getValue());
			}
			while(query.next());
		}

		return ("" + onconnect_answer.CONN_LOGIN_FAILED.getValue()); 
	}
}
