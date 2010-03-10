package windows.actions.menus;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import socket.packet.handlers.sends.contact_handlers.AddContact_handler;

/*
 * Action sur ajout contact du menu
 */
public class menubar_addcontact implements ActionListener {

	private String reponse;

	public void actionPerformed(ActionEvent e) {
	//popup pour recuperer le nom du contact
		reponse = JOptionPane.showInputDialog(null,"Entrez l'identifiant du contact � ajouter :",
				"Nouveau contact",JOptionPane.QUESTION_MESSAGE);
		if (reponse != null && !reponse.equalsIgnoreCase(""))//si la réponse n'est pas vide
		{
			AddContact_handler pck = new AddContact_handler(reponse);//creation du packet
			pck.Send();//envoi du packet
		}	

	}

}
