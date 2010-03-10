package socket.packet;

import java.io.Serializable;

/*
 * packet definition
 */
public class Packet implements Serializable
{

	private static final long serialVersionUID = 1L;
	private Integer opcode;
	private Object data;
	
	public Packet(Integer id, Object dat)
	{
		setOpcode(id);
		setData(dat);
	}

	public void setData(Object data) { this.data = data; }
	public Object getData() { return data; }
	public void setOpcode(Integer opcode) { this.opcode = opcode; }
	public Integer getOpcode() { return opcode; }
}
