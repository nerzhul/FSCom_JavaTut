package windows.actions.click;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class contact_delete implements ActionListener {

	private Object contact;
	private JFrame fenetre;
	public contact_delete(Object contactasupprimer) {
		this.contact=contactasupprimer;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		int reponse = JOptionPane.showConfirmDialog(fenetre, "Voulez-vous vraiment supprimer "+ contact +" ?","Important !!",JOptionPane.YES_NO_OPTION);
		if (reponse == JOptionPane.YES_OPTION)
		{
			//envoi au serveur pour supprimer le "contact"
			JOptionPane.showMessageDialog(fenetre,"Le contact "+ contact +" a été supprimé avec succès !");
		}
	}
}
