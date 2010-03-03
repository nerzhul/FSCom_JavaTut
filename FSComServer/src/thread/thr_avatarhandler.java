package thread;

import java.util.Vector;

import socket.packet.TransferObjects.Avatar;

public class thr_avatarhandler {

	Vector<Avatar> v_ava;
	public thr_avatarhandler()
	{
		v_ava = new Vector<Avatar>();
	}
	
	public void AddAvatar(Avatar av)
	{
		if(av != null)
			v_ava.add(av);
	}
	
	public Avatar getAvatarByUID(Integer uid)
	{
		if(!v_ava.isEmpty())
		{
			synchronized(v_ava)
			{
				for(Avatar av: v_ava)
					if(av.getUid().equals(uid))
						return av;
			}
		}
		
		return null;
	}
}
