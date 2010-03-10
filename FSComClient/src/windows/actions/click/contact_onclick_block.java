package windows.actions.click;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import session.contact;
import socket.packet.handlers.sends.contact_handlers.BlockContact_handler;

/*
 * Action de bloquage/débloquage d'un contact
 */
public class contact_onclick_block implements ActionListener {

	private contact contact;
	public contact_onclick_block(contact ct) {
		//stockage du contact
		this.contact = ct;
	}

	public void actionPerformed(ActionEvent e) 
	{
		Integer method = 0;
		if(!contact.isBlocked())//si il est pas bloqué on le bloque
			method = 1;
		BlockContact_handler pck = new BlockContact_handler(contact.getCid(),method);//création du packet
		pck.Send();//envoi du packet
	}

}
