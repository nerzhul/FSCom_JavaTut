package session;

import java.util.Vector;

import socket.packet.handlers.senders.AvatarAnswer_handler;
import socket.packet.objects.Avatar;

public class AvatarHandler 
{
	private static Vector<Avatar> v_ava;
	public AvatarHandler()
	{
		v_ava = new Vector<Avatar>();
	}
	
	public static void AddAvatar(Avatar av)
	{
		if(av != null)
		{
			if(v_ava.isEmpty())
				v_ava.add(av);
			else
			{
				synchronized(v_ava)
				{
					for(Avatar a: v_ava)
						if(av.getUid().equals(a.getUid()))
						{
							a.setImg(av.getImg());
							
							Vector<session> v_sess = SessionHandler.getContactByUID(a.getUid()).getLinkedSessions();
							synchronized(v_sess)
							{
								AvatarAnswer_handler pck = new AvatarAnswer_handler(a);
								for(session s: v_sess)
									pck.Send(s.getSocket());
							}
							return;
						}
					v_ava.add(av);			
				}
			}
		}
	}
	
	public static Avatar getAvatarByUID(Integer uid)
	{
		if(!v_ava.isEmpty())
		{
			synchronized(v_ava)
			{
				for(Avatar av: v_ava)
				{
					if(av.getUid().equals(uid))
						return av;
				}
					
			}
		}
		return null;
	}
}
