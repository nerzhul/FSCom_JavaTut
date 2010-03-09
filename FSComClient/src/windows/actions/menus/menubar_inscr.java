package windows.actions.menus;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import thread.windowthread;
import windows.forms.form_inscription;

public class menubar_inscr implements ActionListener {

	public void actionPerformed(ActionEvent arg0) {	
		if(windowthread.getFmConn().getPanConnect().getFmInsc() == null)
			windowthread.getFmConn().getPanConnect().setFmInsc(new form_inscription()); 
	}

}
