package socket.packet.objects;

import java.io.Serializable;
import java.util.Vector;

import database.DatabaseTransactions;

import session.SessionHandler;
import session.contact;
import session.group;
import session.session;

/*
 * basic object which contains all client needed datas
 */
public class ClientDatas implements Serializable
{
	private static final long serialVersionUID = 1L;
	private String pseudo;
	private String pperso;
	private Integer status;
	private Vector<group> grp;
	private Vector<contact> ct;
	
	public ClientDatas() {}
	public ClientDatas(session sess)
	{
		setStatus(sess.getStatus());
		setPseudo(sess.getPseudo());
		setPperso(sess.getPersonnalMsg());
		GetMyGroups(sess);
		GetMyContacts(sess);
	}
		
	private void GetMyGroups(session sess)
	{
		grp = new Vector<group>();
		Vector<Integer> v_grp = DatabaseTransactions.getIntegerList("acc_group",
				"gid"," uid = '" + sess.getUid() + "'");
		for(int i=0;i<v_grp.size();i++)
		{
			String acc_group_get = "uid = '" + sess.getUid() + "' AND gid = '" + v_grp.get(i) + "'";
			
			group tmp_grp = new group(v_grp.get(i),DatabaseTransactions.StringQuery("acc_group",
					"name", acc_group_get));
			grp.add(tmp_grp);
		}
	}
	
	private void GetMyContacts(session sess)
	{
		ct = new Vector<contact>();
		Vector<Integer> v_cont = DatabaseTransactions.getIntegerList("acc_contact",
				"contact"," uid = '" + sess.getUid() + "'");
		
		for(int i=0;i<v_cont.size();i++)
		{
			String acc_contact_get = "uid = '" + sess.getUid() + "' AND contact = '" + v_cont.get(i) + "'";
			contact tmp_contact = new contact(v_cont.get(i), 
					DatabaseTransactions.IntegerQuery("acc_blocked", "blocked", acc_contact_get), 
					DatabaseTransactions.StringQuery("account",	"pseudo", "uid = '" + v_cont.get(i) + "'"),
					DatabaseTransactions.StringQuery("account", "phr_perso", "uid = '" + v_cont.get(i) + "'"),
					DatabaseTransactions.StringQuery("acc_contact", "comment", acc_contact_get),
					(SessionHandler.isConnected(v_cont.get(i)) ? SessionHandler.getContactByUID(v_cont.get(i)).getStatus() : 0),
					DatabaseTransactions.IntegerQuery("acc_contact", "group", acc_contact_get),
					DatabaseTransactions.StringQuery("account", "user", "uid = '" + v_cont.get(i) + "'"));
			ct.add(tmp_contact);
		}
	}
	public void setPseudo(String pseudo) { this.pseudo = pseudo; }
	public String getPseudo() { return pseudo; }
	public void setPperso(String pperso) { this.pperso = pperso; }
	public String getPperso() { return pperso; }
	public void setStatus(Integer status) { this.status = status; }
	public Integer getStatus() { return status; }
	
}
