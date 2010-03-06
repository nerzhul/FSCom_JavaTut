package socket.packet;

import java.io.Serializable;

public class ConnectData implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String name,persoP,pseudo;
	private Integer uid,status;
	public ConnectData(String _name, Integer _status, String _pp, Integer _uid, String _pseudo) 
	{
		name = _name;
		pseudo = _pseudo;
		status = _status;
		persoP = _pp;
		uid = _uid;
	}
	public String getName() { return name; }
	public Integer getStatus() { return status; }
	public String getPersoP() { return persoP; }
	public Integer getUid() { return uid; }
	public String getPseudo() { return pseudo; }
}
