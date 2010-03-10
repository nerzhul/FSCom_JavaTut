package session;

import java.io.Serializable;
import java.util.Vector;


public class group implements Serializable{

	private static final long serialVersionUID = 1L;
	private Vector<contact> contacts;
	private Integer gid;
	private String gname;
	
	//on définit l'objet groupe
	public group(Integer _gid, String _gname) 
	{
		contacts = new Vector<contact>();//contenant des contacts
		setGid(_gid);//etant identifié par un gid
		setGname(_gname);//et ayant un nom
	}
	
	//fonction d'ajour de contact dans un le groupe
	public void AddContact(contact cnt)
	{
		if(contacts == null)//si le vecteur n'existe pas on le créait
			contacts = new Vector<contact>();
		if(cnt != null)//puis si le contact existe on l'ajout au groupe
			contacts.add(cnt);
	}
	
	//fonction permettant de récupérer les contacts du groupe
	public Vector<contact> getContacts() 
	{
		if(contacts == null)//si le vecteur existe on le retourne
			contacts = new Vector<contact>();
		return contacts;	
	}
	
	//fonction d'accès aux données
	public void setGid(Integer gid) { this.gid = gid; }
	public Integer getGid() { return gid; }
	public void setGname(String gname) { this.gname = gname; }
	public String getGname() { return gname; }
	public String toString() { return getGname(); }
}
