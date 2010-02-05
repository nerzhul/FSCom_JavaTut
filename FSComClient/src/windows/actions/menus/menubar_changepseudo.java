package windows.actions.menus;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import socket.packet.handlers.send_handler;
import socket.packet.handlers.sends.changepseudo_handler;

public class menubar_changepseudo extends menubar_main implements ActionListener {

	private JFrame window;
	private String newpseud;
	
	public menubar_changepseudo(){}

	public void actionPerformed(ActionEvent e) {
		newpseud = JOptionPane.showInputDialog(window,"Entrez votre nouveau pseudo :",
				"Nouveau pseudo",JOptionPane.QUESTION_MESSAGE);
		if (newpseud != null && !newpseud.equalsIgnoreCase("")) 
		{
			if(newpseud.length()<20 && newpseud.length() > 1)
			{
				send_handler pck = new changepseudo_handler(newpseud);
				if(pck != null)
					pck.Send();
			}
			else
				JOptionPane.showMessageDialog(window,"Longueur de pseudo invalide" );
		}	
	}

}
