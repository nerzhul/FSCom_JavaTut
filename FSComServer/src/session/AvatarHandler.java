package session;

import java.util.Vector;

import socket.packet.handlers.senders.AvatarAnswer_handler;
import socket.packet.objects.Avatar;

/*
 * Manage avatars
 */
public class AvatarHandler 
{
	// store avatars into a vector 
	private static Vector<Avatar> v_ava;
	public AvatarHandler()
	{
		v_ava = new Vector<Avatar>();
	}
	
	// Adding avatar
	public static void AddAvatar(Avatar av)
	{
		if(av == null)
			return;
		// if no avatar add it without test
		if(v_ava.isEmpty())
			v_ava.add(av);
		else
		{
			synchronized(v_ava)
			{
				// search avatar
				for(Avatar a: v_ava)
					if(av.getUid().equals(a.getUid()))
					{
						// if avatar for this user exist, update avatar
						a.setImg(av.getImg());
						
						Vector<session> v_sess = SessionHandler.getContactByUID(a.getUid()).getLinkedSessions();
						synchronized(v_sess)
						{
							// say we have got the avatar
							AvatarAnswer_handler pck = new AvatarAnswer_handler(a);
							for(session s: v_sess)
								pck.Send(s.getSocket());
						}
						return;
					}
				// there is'nt avatar we add it
				v_ava.add(av);			
			}
		}
	}
	
	// we want to get avatar for user id
	public static Avatar getAvatarByUID(Integer uid)
	{
		synchronized(v_ava)
		{
			if(!v_ava.isEmpty())
				for(Avatar av: v_ava)
					if(av.getUid().equals(uid))
						return av;
		}
		return null;
	}
}
