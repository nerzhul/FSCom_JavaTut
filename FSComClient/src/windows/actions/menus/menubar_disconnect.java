package windows.actions.menus;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import session.events;
import thread.windowthread;
import windows.forms.form_communicate;
import windows.forms.form_master;

/*
 * Action sur déconnection (menu)
 */
public class menubar_disconnect implements ActionListener {

	private form_master window;
	public menubar_disconnect(form_master fram) 
	{
		this.window = fram;
	}

	public void actionPerformed(ActionEvent e) 
	{
		events.DisconnectCurrentClient();//on effectue les divers actions a la déconnection (fermeture socket..)
		form_communicate fmCom = windowthread.getFmConn().getPanContact().getComm();
		if(fmCom != null)//si il y a des onglets on les fermes
			fmCom.closepan();		
		window.BuildPanel(1);//et on refait le panel de connection
	}
}
