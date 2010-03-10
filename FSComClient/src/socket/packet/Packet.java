package socket.packet;

import java.io.Serializable;
//classe définissant un paquet
public class Packet implements Serializable
{

	private static final long serialVersionUID = 1L;
	private Integer opcode;
	private Object data;
	
	public Packet(Integer id, Object dat)
	{
		setOpcode(id);//contenant un id  
		setData(dat);//et les données
	}

	//fonctions d'accès aux variables
	public void setData(Object data) { this.data = data; }
	public Object getData() { return data; }
	public void setOpcode(Integer opcode) { this.opcode = opcode; }
	public Integer getOpcode() { return opcode; }
}
