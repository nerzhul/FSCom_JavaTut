package windows.actions.click;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import session.contact;
import socket.packet.handlers.sends.BlockContact_handler;

public class contact_onclick_deblock implements ActionListener {

	private contact ct;
	public contact_onclick_deblock(contact user) {
		this.ct = user;
	}

	public void actionPerformed(ActionEvent e) 
	{
		/*TO DO : debloquer !*/
	}

}
