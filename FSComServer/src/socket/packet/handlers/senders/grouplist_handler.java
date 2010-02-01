package socket.packet.handlers.senders;

import java.util.Vector;

import database.DatabaseTransactions;
import session.session;
import socket.packet.handlers.send_handler;

public class grouplist_handler extends send_handler {

	public grouplist_handler(session sess) {
		m_sess = sess;
		opcode = 0x0F;
		data = getGroupList();
	}

	private String getGroupList() 
	{
		String pck = "";
		Vector<Integer> v_grp = DatabaseTransactions.getIntegerList("acc_group",
				"gid"," uid = '" + m_sess.getUid() + "'");
		
		for(int i=0;i<v_grp.size();i++)
		{
			String acc_group_get = "uid = '" + m_sess.getUid() + "' AND gid = '" + v_grp.get(i) + "'";
			
			pck += v_grp.get(i);
			pck += "/.";
			pck += DatabaseTransactions.StringQuery("acc_group", "name", acc_group_get);
			pck += "/./.";
		}		
		pck += "00";
		
		return pck;
	}

}
