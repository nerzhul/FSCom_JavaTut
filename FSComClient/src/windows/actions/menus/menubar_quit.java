package windows.actions.menus;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class menubar_quit implements ActionListener{
	
	public void actionPerformed(ActionEvent e)
	{ 
		/* 
		 * TODO : confirm window
		 * clean socket correctly
		 */
		
		System.exit(0);
	} 
}
