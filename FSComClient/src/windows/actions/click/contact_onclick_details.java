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
		JOptionPane.showMessageDialog(null,"Pseudo : " + contact.getPseudo()+"\n"+ "Message personnel : " + contact.getMsg_perso() );
		/*TO DO :
		 *	affichage de l'identifiant (servant a se connecté + a etre ajouté) 
		*/
	}

}
