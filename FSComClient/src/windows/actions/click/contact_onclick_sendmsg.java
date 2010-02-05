package windows.actions.click;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import session.objects.contact;

import windows.forms.form_communicate;
import windows.forms.panel_contact;

//import windows.window.form_conversation_avec;

public class contact_onclick_sendmsg implements ActionListener {

	private contact contact;
	private panel_contact mypn;
	public contact_onclick_sendmsg(contact ct, panel_contact pn) {
		this.contact = ct;
		this.mypn = pn;
	}


	public void actionPerformed(ActionEvent e) {
		if(mypn.getComm() == null)
			mypn.setComm(new form_communicate());
		
		mypn.getComm().AddTab(contact);

	}
}

