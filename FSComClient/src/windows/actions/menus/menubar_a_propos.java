package windows.actions.menus;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class menubar_a_propos extends menubar_main implements ActionListener {
	
	public void actionPerformed(ActionEvent e) 
	{
		JOptionPane.showMessageDialog(null,"R�alis� par Jean-Baptiste Blandureau et Loic Blot dans le cadre de leur projet tuteur� de 2�me ann�e � l'IUT informatique d'Amiens.");
	}

}
