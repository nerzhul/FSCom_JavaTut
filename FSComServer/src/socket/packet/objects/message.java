package socket.packet.objects;

import java.io.Serializable;

public class Message implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String msg;
	private Integer dest;
	public Message(String text, Integer destin)
	{
		this.setMsg(text);
		this.setDest(destin);
	}
	public void setMsg(String msg) { this.msg = msg; }
	public String getMsg() { return msg; }
	public void setDest(Integer dest) { this.dest = dest; }
	public Integer getDest() { return dest; }
}
