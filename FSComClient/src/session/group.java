package session;

import java.io.Serializable;
import java.util.Vector;


public class group implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Vector<contact> contacts;
	private Integer gid;
	private String gname;
	
	public group(Integer _gid, String _gname) 
	{
		contacts = new Vector<contact>();
		setGid(_gid);
		setGname(_gname);
	}

	public void AddContact(contact cnt)
	{
		if(contacts == null)
			contacts = new Vector<contact>();
		if(cnt != null)
			contacts.add(cnt);
	}
	
	public Vector<contact> getContacts() 
	{
		if(contacts == null)
			contacts = new Vector<contact>();
		return contacts;	
	}
	
	public void setGid(Integer gid) { this.gid = gid; }
	public Integer getGid() { return gid; }
	public void setGname(String gname) { this.gname = gname; }
	public String getGname() { return gname; }
	public String toString() { return getGname(); }
}
