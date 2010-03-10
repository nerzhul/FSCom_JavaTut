package windows.actions.menus;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import socket.Sender;
import socket.packet.handlers.sends.Disconnect_handler;

/*
 * Action de quitter (menu)
 */
public class menubar_quit implements ActionListener, WindowListener
{
	public void actionPerformed(ActionEvent e)
	{ 
		close();
	}

	public void close(){
		Disconnect_handler pck = new Disconnect_handler();//creation du packet de déconnection
		pck.Send();//envoi du packet
		Sender.StopSocket();//on stop le sender
		System.exit(0);//on quit le programme
	}
	
	public void windowClosing(WindowEvent e) {
		close();		
	}
	
	public void windowActivated(WindowEvent e) {}
	public void windowClosed(WindowEvent e) {}
	public void windowDeactivated(WindowEvent e) {}
	public void windowDeiconified(WindowEvent e) {}
	public void windowIconified(WindowEvent e) {}
	public void windowOpened(WindowEvent e) {} 
}
