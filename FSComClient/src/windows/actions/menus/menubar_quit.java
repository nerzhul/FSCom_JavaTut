package windows.actions.menus;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import socket.Sender;
import socket.packet.handlers.sends.Disconnect_handler;

public class menubar_quit implements ActionListener
{
	public void actionPerformed(ActionEvent e)
	{ 
		Disconnect_handler pck = new Disconnect_handler();
		pck.Send();
		Sender.StopSocket();
		
		System.exit(0);
	} 
}
