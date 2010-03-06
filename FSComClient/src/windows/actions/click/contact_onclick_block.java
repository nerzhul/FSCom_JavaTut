package windows.actions.click;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import session.contact;
import socket.packet.handlers.sends.contact_handlers.BlockContact_handler;

public class contact_onclick_block implements ActionListener {

	private contact contact;
	public contact_onclick_block(contact ct) {
		this.contact = ct;
	}

	public void actionPerformed(ActionEvent e) 
	{
		Integer method = 0;
		if(!contact.isBlocked())
			method = 1;
		BlockContact_handler pck = new BlockContact_handler(contact.getCid(),method);
		pck.Send();
	}

}
