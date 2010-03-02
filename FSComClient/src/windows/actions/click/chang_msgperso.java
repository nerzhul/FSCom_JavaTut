package windows.actions.click;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;

import socket.packet.handlers.sends.ChangeMsgPerso_handler;

public class chang_msgperso implements MouseListener {

	private String newmsgperso;
	public void mouseClicked(MouseEvent e) {
		newmsgperso = JOptionPane.showInputDialog(null,"Entrez votre nouveau message personnel :",
				"Nouveau pseudo",JOptionPane.QUESTION_MESSAGE);
		if (newmsgperso != null && !newmsgperso.equalsIgnoreCase("")) 
		{
			if(newmsgperso.length()<25 && newmsgperso.length() > 1)
			{
				ChangeMsgPerso_handler pck = new ChangeMsgPerso_handler(newmsgperso);
				pck.Send();
			}
			else
				JOptionPane.showMessageDialog(null,"Longueur de message personnel invalide" );
		}	
	}

	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}

}
