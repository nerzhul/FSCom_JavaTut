package windows.actions.menus;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import misc.Misc;

/*
 * Action sur apropos du menu
 */
public class menubar_a_propos extends menubar_main implements ActionListener {
	
	public void actionPerformed(ActionEvent e) 
	{
		//affichage d'un popup avec des informations
		JOptionPane.showMessageDialog(null,"R�alis� par Jean-Baptiste Blandureau \net Lo�c Blot dans le cadre de leur \nprojet tutor� de 2�me ann�e � \nl'IUT informatique d'Amiens.\n\n Version du client : "+ Misc.GetVersion());
	}

}
