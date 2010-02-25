package windows.actions.menus;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import session.events;
import windows.forms.form_master;

public class menubar_disconnect implements ActionListener {

	private form_master window;
	public menubar_disconnect(form_master fram) 
	{
		this.window = fram;
	}

	public void actionPerformed(ActionEvent e) 
	{
		events.DisconnectCurrentClient();
		window.BuildPanel(1);
	}

}
