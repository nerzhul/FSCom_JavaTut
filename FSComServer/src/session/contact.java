package session;

import java.io.Serializable;

public class contact implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	private Integer cid;
	@SuppressWarnings("unused")
	private boolean blocked;
	@SuppressWarnings("unused")
	private String pseudo;
	@SuppressWarnings("unused")
	private String msg_perso;
	@SuppressWarnings("unused")
	private Integer status;
	@SuppressWarnings("unused")
	private String comment;
	private Integer group;
	@SuppressWarnings("unused")
	private String name;
	
	public contact(Integer _cid, Integer _blocked, String _pseudo, String _msg_perso,
			String _comment, Integer _status, Integer _grp, String nm)
	{
		cid = _cid;
		blocked = (_blocked == 1) ? true : false;
		pseudo = _pseudo;
		msg_perso = _msg_perso;
		comment = _comment;
		status = _status;
		setGroup(_grp);
		name = nm;
	}
	public void setCid(Integer cid) { this.cid = cid; }
	public void setBlocked(boolean blocked) { this.blocked = blocked; }
	public void setPseudo(String pseudo) { this.pseudo = pseudo; }
	public void setMsg_perso(String msg_perso) { this.msg_perso = msg_perso; }
	public void setStatus(Integer status) {	this.status = status; }
	public void setGroup(Integer group) { this.group = group; }
	public Integer getGroup() { return group; }
}
