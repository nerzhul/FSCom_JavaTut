package session.objects;

import java.util.Vector;

public class group {

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
		if(cnt != null)
			contacts.add(cnt);
	}
	
	public Vector<contact> getContacts() { return contacts;	}
	public void setGid(Integer gid) { this.gid = gid; }
	public Integer getGid() { return gid; }
	public void setGname(String gname) { this.gname = gname; }
	public String getGname() { return gname; }
}
