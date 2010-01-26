package windows.actions.menus;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import socket.packet.handlers.send_handler;
import socket.packet.handlers.sends.changepseudo_handler;

public class menubar_changepseudo extends menubar_main implements ActionListener {

	private JFrame fenetre;
	private String newpseud;
	private JLabel newtitle;
	
	public menubar_changepseudo(JLabel lab)
	{
		newtitle=lab;
	}

	public void actionPerformed(ActionEvent e) {
		newpseud = JOptionPane.showInputDialog(fenetre,"Entrez votre nouveau pseudo :",
				"Nouveau pseudo",JOptionPane.QUESTION_MESSAGE);
		if (newpseud != null && !newpseud.equalsIgnoreCase("")) 
		{
			if(newpseud.length()<20)
			{
				//envoi au serveur du nouveau pseudo
				newtitle.setText("Vous etes connect� en tant que "+ newpseud + " !");
				JOptionPane.showMessageDialog(fenetre,"Vous etes maintenant connu sous le nom de "+ newpseud +" !" );
				send_handler pck = new changepseudo_handler(newpseud);
				if(pck != null)
					pck.Send();
			}
			else
				JOptionPane.showMessageDialog(fenetre,"Nouveau pseudo trop long !" );
		}	
	}

}
