package socket.packet.handlers;

import java.util.Vector;

import database.DatabaseTransactions;
import session.session;

public class contactlist_handler extends send_handler {

	public contactlist_handler(session sess) {
		m_sess = sess;
		opcode = 0x0C;
		data = getContactList();
	}

	private String getContactList() 
	{
		String pck = "";
		Vector<Integer> v_cont = DatabaseTransactions.getIntegerList("acc_contact",
				"contact"," uid = '" + m_sess.getUid() + "'");
		
		for(int i=0;i<v_cont.size();i++)
		{
			String acc_contact_get = "uid = '" + m_sess.getUid() + "' AND contact = '" + v_cont.get(i) + "'";
			
			pck += DatabaseTransactions.StringQuery("acc_contact", "blocked", acc_contact_get);
			pck += "%";
			pck += DatabaseTransactions.StringQuery("account",
					"pseudo", "uid = '" + v_cont.get(i) + "'");
			pck += "%";
			pck += DatabaseTransactions.StringQuery("acc_contact", "comment", acc_contact_get);
			pck += "%";
			pck += DatabaseTransactions.IntegerQuery("acc_contact", "group", acc_contact_get);
			pck += "ù%ù";
		}		
			
		if(pck.equals(""))
			pck = "00";
		
		return pck;
	}

}
