package windows.actions.click;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import session.group;
import socket.packet.handlers.sends.DelGroup_handler;

public class group_onclick_delete implements ActionListener 
{
	private Integer _gid;
	public group_onclick_delete(group gr) 
	{
		_gid = gr.getGid();
	}

	public void actionPerformed(ActionEvent arg0) 
	{
		DelGroup_handler dhl = new DelGroup_handler(_gid);
		dhl.Send();
	}

}
