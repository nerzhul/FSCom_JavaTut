package session;

import java.io.Serializable;

/*
 * group object for transaction between client and server
 */
public class group implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer gid;
	private String gname;
	public group(Integer _gid, String _gname)
	{
		setGid(_gid);
		setGname(_gname);
	}
	public void setGid(Integer gid) { this.gid = gid; }
	public Integer getGid() { return gid; }
	public void setGname(String gname) { this.gname = gname; }
	public String getGname() { return gname; }
}
