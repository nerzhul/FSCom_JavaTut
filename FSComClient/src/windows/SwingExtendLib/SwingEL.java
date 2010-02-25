package windows.SwingExtendLib;

import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

public class SwingEL 
{
	public SwingEL() {}
	
	public static void AddItemToMenu(JPopupMenu pop, String txt, ActionListener lst)
	{
		JMenuItem ji = new JMenuItem(txt);
		ji.addActionListener(lst);
		pop.add(ji);
	}
	
	public static void AddItemToMenuBar(JMenu pop, String txt, ActionListener lst)
	{
		JMenuItem ji = new JMenuItem(txt);
		ji.addActionListener(lst);
		pop.add(ji);
	}
}
