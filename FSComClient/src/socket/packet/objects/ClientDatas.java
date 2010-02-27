package socket.packet.objects;

import java.io.Serializable;
import java.util.Vector;

import session.contact;
import session.group;

public class ClientDatas implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String pseudo;
	private String pperso;
	private Integer status;
	private Vector<group> grp;
	private Vector<contact> ct;
	public ClientDatas(){}
		
	public Vector<group> GetMyGroups(){ return grp;	 }
	
	public Vector<contact> GetMyContacts()	{ return ct; }
	public String getPseudo() { return pseudo; }
	public String getPperso() { return pperso; }
	public Integer getStatus() { return status; }
	
}
