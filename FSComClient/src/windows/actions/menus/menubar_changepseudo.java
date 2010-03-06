package windows.actions.menus;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import socket.packet.handlers.Send_handler;
import socket.packet.handlers.sends.client_handlers.ChangePseudo_handler;

public class menubar_changepseudo extends menubar_main implements ActionListener, MouseListener  {

	private String newpseud;
	
	public menubar_changepseudo(){}

	public void actionPerformed(ActionEvent e) { ChangePseudo(); }
	private void ChangePseudo()
	{
		newpseud = JOptionPane.showInputDialog(null,"Entrez votre nouveau pseudo :",
				"Nouveau pseudo",JOptionPane.QUESTION_MESSAGE);
		if (newpseud != null && !newpseud.equalsIgnoreCase("")) 
		{
			if(newpseud.length()<20 && newpseud.length() > 1)
			{
				Send_handler pck = new ChangePseudo_handler(newpseud);
				if(pck != null)
					pck.Send();
			}
			else
				JOptionPane.showMessageDialog(null,"Longueur de pseudo invalide" );
		}	
	}

	public void mouseClicked(MouseEvent arg0) { ChangePseudo(); }
	public void mouseEntered(MouseEvent arg0) {}
	public void mouseExited(MouseEvent arg0) {}
	public void mousePressed(MouseEvent arg0) {}
	public void mouseReleased(MouseEvent arg0) {}

}
