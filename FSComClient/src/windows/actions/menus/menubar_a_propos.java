package windows.actions.menus;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class menubar_a_propos extends menubar_main implements ActionListener {
	
	public void actionPerformed(ActionEvent e) 
	{
		JOptionPane.showMessageDialog(null,"Réalisé par Jean-Baptiste Blandureau \net Loïc Blot dans le cadre de leur \nprojet tutoré de 2ème année à \nl'IUT informatique d'Amiens.");
	}

}
