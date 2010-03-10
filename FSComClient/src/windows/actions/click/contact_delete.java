package windows.actions.click;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import session.contact;
import socket.packet.handlers.sends.contact_handlers.DelContact_handler;

/*
 * Action de suppression d'un contact
 */
public class contact_delete implements ActionListener 
{
	private contact contact;
	public contact_delete(contact toDel) {
		//stockage du contact a supprimer
		this.contact = toDel;
	}

	public void actionPerformed(ActionEvent e) {
		//affichage d'une fenetre de confirmation
		if (JOptionPane.showConfirmDialog(null, "Voulez-vous vraiment supprimer "
				+ contact +" ?","Important !!",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
		{
			DelContact_handler dch = new DelContact_handler(contact.getCid());//création du packet
			if(dch != null)
				dch.Send();//et envoi
		}
	}
}
