package socket.packet.objects;

import java.io.Serializable;

/*
 * basic object which contain one ID and one Data
 */
public class IdAndData  implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private Integer uid;
	private String dat;
	public IdAndData(Integer _uid, String _dat)
	{
		setUid(_uid);
		setDat(_dat);
	}
	public void setUid(Integer uid) { this.uid = uid; }
	public Integer getUid() { return uid; }
	public void setDat(String dat) { this.dat = dat; }
	public String getDat() { return dat; }

}
