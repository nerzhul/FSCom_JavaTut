package windows.actions.menus;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class menubar_a_propos extends menubar_main implements ActionListener {
	
	public void actionPerformed(ActionEvent e) 
	{
		JOptionPane.showMessageDialog(null,"R�alis� par Jean-Baptiste Blandureau \net Loic Blot dans le cadre de leur \nprojet tuteur� de 2�me ann�e � \nl'IUT informatique d'Amiens.");
	}

}
