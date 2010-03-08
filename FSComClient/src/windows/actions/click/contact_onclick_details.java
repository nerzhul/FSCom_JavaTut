package windows.actions.click;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import session.contact;

public class contact_onclick_details implements ActionListener 
{
	private contact contact;
	public contact_onclick_details(contact contactavoir) 
	{
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
		JOptionPane.showMessageDialog(null,"Identifiant : "+contact.getName()+"\nPseudo : " + contact.getPseudo()+"\nMessage personnel : " + contact.getMsg_perso() +"\nStatuts : "+stat);
	}

}
