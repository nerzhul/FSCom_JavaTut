package socket.packet;

import java.io.Serializable;

public class packet implements Serializable
{

	private static final long serialVersionUID = 1L;
	private Integer opcode;
	private Object data;
	
	public packet(Integer id, Object dat)
	{
		setOpcode(id);
		setData(dat);
	}

	public void setData(Object data) { this.data = data; }
	public Object getData() { return data; }
	public void setOpcode(Integer opcode) { this.opcode = opcode; }
	public Integer getOpcode() { return opcode; }
}
