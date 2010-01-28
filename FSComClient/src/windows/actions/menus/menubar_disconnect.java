package windows.actions.menus;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import session.events;

import windows.forms.form_connect;

public class menubar_disconnect implements ActionListener {

	private JFrame window;
	public menubar_disconnect(JFrame fram) 
	{
		this.window=fram;
	}

	public void actionPerformed(ActionEvent e) 
	{
		events.DisconnectCurrentClient();
		window.dispose();
		new form_connect();
	}

}
