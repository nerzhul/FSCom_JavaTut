package windows.actions.click;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import session.contact;

import windows.forms.form_communicate;
import windows.forms.panel_contact;

/*
 * Action envoi d'un message a un contact (par doubleclick sur celui-ci ou dans le menu de click droit)
 */
public class contact_onclick_sendmsg implements ActionListener {

	private contact contact;
	private panel_contact mypn;
	public contact_onclick_sendmsg(contact ct, panel_contact pn) {
		//stockage des données
		this.contact = ct;
		this.mypn = pn;
	}

	public void actionPerformed(ActionEvent e) 
	{
		//si il n'y a pas de fenetre de conversation on en créait une
		if(mypn.getComm() == null)
			mypn.setComm(new form_communicate());
		
		mypn.getComm().AddTab(contact);//et on ajoute un onglet avec le contact selectionné
		mypn.getComm().ChangeAllMyStatusBorder();
	}
}

