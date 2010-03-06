package socket.packet.objects;

import java.io.Serializable;

public class AccCreateDatas implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String acc_name,password;
	
	public AccCreateDatas(String name,String pwd)
	{
		acc_name = name;
		password = pwd;
	}
}
