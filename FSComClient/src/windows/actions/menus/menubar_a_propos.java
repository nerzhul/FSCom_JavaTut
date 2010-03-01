package windows.actions.menus;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class menubar_a_propos extends menubar_main implements ActionListener {
	
	public void actionPerformed(ActionEvent e) 
	{
		JOptionPane.showMessageDialog(null,"DUT 2008-2010");
	}

}
