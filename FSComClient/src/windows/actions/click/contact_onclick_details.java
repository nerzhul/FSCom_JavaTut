package windows.actions.click;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import session.contact;

/*
 * Action pour voir les détails sur un contact
 */
public class contact_onclick_details implements ActionListener 
{
	private contact contact;
	public contact_onclick_details(contact contactavoir) 
	{
		//stockage du contact
		this.contact=contactavoir;
	}

	public void actionPerformed(ActionEvent e) {
		Integer status = contact.getStatus();
		String stat ="";
		if (status.equals(0) || status.equals(4))
			stat="Offline";
	    else if (status.equals(1))
	    		stat="Online";
	    else if (status.equals(2))
	    		stat="Busy";
	    else if (status.equals(3))
	    		stat="Idle";
		//affichage d'une popup avec toutes les informations sur le contact
		JOptionPane.showMessageDialog(null,"Identifiant : "+contact.getName()+"\nPseudo : " + contact.getPseudo()+"\nMessage personnel : " + contact.getMsg_perso() +"\nStatuts : "+stat);
	}

}
