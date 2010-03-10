package session;

import java.io.Serializable;

public class contact implements Serializable
{

	private static final long serialVersionUID = 1L;
	private Integer cid;
	private boolean blocked;
	private String pseudo;
	private String msg_perso;
	private Integer status;
	private String comment;
	private Integer group;
	private String name;
	
	//on définie l'objet contact
	public contact(Integer _cid, Integer _blocked, String _pseudo, String _msg_perso,
			String _comment, Integer _status, Integer _grp, String _name)
	{
		cid = _cid;
		blocked = (_blocked == 1) ? true : false;
		pseudo = _pseudo;
		msg_perso = _msg_perso;
		comment = _comment;
		status = _status;
		setGroup(_grp);
		name = _name;
	}
	//on définie toutes les fonctions de récupérations et d'initialisation de données sur un contact
	public void setCid(Integer cid) { this.cid = cid; }
	public Integer getCid() { return cid; }
	public void setBlocked(boolean blocked) { this.blocked = blocked; }
	public boolean isBlocked() { return blocked; }
	public void setPseudo(String pseudo) { this.pseudo = pseudo; }
	public String getPseudo() { return pseudo; }
	public void setMsg_perso(String msg_perso) { this.msg_perso = msg_perso; }
	public String getMsg_perso() { return msg_perso; }
	public void setStatus(Integer status) {	this.status = status; }
	public Integer getStatus() { return status; }
	public void setComment(String comment) { this.comment = comment; }
	public String getComment() { return comment; }
	public void setGroup(Integer group) { this.group = group; }
	public Integer getGroup() { return group; }
	public String toString() { return getPseudo()+"("+msg_perso+")"; }
	public void setName(String nm) { this.name = nm; }
	public String getName() { return name; }
}
