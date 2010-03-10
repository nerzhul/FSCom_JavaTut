package windows.actions.click;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;
import socket.packet.handlers.sends.client_handlers.ChangeMsgPerso_handler;

/*
 * Action sur le click sur le message perso ou en passant par le menu : pour changer notre message personnel
 */
public class chang_msgperso implements MouseListener, ActionListener {

	private String newmsgperso;
	public void mouseClicked(MouseEvent e) {
		changperso();
	}
	
	public void actionPerformed(ActionEvent arg0) {
		changperso();
	}
	
	public void changperso(){
		//popup pour avoir le nouveau
		newmsgperso = JOptionPane.showInputDialog(null,"Entrez votre nouveau message personnel :",
				"Nouveau pseudo",JOptionPane.QUESTION_MESSAGE);
		if (newmsgperso != null && !newmsgperso.equalsIgnoreCase("")) 
		{//vérifiactions si le nouveau n'est pas vide et si il est pas trop grand/petit
			if(newmsgperso.length()<25 && newmsgperso.length() > 1)
			{
				ChangeMsgPerso_handler pck = new ChangeMsgPerso_handler(newmsgperso);//création du packet
				pck.Send();//envoi du packet
			}
			else
				JOptionPane.showMessageDialog(null,"Longueur de message personnel invalide" );//message d'erreur si le nouveau est invalide
		}	
	}

	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	}
