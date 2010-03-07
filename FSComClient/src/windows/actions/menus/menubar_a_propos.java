package windows.actions.menus;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class menubar_a_propos extends menubar_main implements ActionListener {
	
	public void actionPerformed(ActionEvent e) 
	{
		JOptionPane.showMessageDialog(null,"Réalisé par Jean-Baptiste Blandureau et Loic Blot dans le cadre de leur projet tuteuré de 2ème année à l'IUT informatique d'Amiens.");
	}

}
