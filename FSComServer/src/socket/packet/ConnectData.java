package socket.packet;

import java.io.Serializable;

public class ConnectData implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@SuppressWarnings("unused")
	private String name,persoP,pseudo;
	@SuppressWarnings("unused")
	private Integer uid,status;
	public ConnectData(String _name, Integer _status, String _pp, Integer _uid, String _pseudo) 
	{
		setName(_name);
		setStatus(_status);
		setPersoP(_pp);
		setUid(_uid);
		pseudo = _pseudo;
	}
	public void setName(String name) { this.name = name; }
	public void setStatus(Integer status) { this.status = status; }
	public void setPersoP(String persoP) { this.persoP = persoP; }
	public void setUid(Integer uid) { this.uid = uid; }
}
