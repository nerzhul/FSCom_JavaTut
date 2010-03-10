package windows.actions.buttons;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import net.infonode.tabbedpanel.TabbedPanel;

/*
 * Action sur la fermeture de la fenetre contenant les onglets de conversation
 */
public class CloseAllConversTabs_button implements WindowListener {
	
	private JFrame window;
	private TabbedPanel tab;
	public CloseAllConversTabs_button(JFrame frame, TabbedPanel tabPan) {
		window=frame;
		tab=tabPan;
	}

	public void windowClosing(WindowEvent arg0) 
	{
		if(tab.getTabCount() == 1)//si il y a qu'un seul onglet on le remove et on ferme la fenetre
		{
			tab.removeTab(tab.getTabAt(0));
			window.dispose();
			return;
		}
		//par contre si c'est pas le cas on demande si l'utilisateur veut vraiment fermer tout les onglets
        if (JOptionPane.showConfirmDialog(null,"Vous allez fermer toutes " +
        		"vos fen�tres de conversation, �tes vous sur ?","Confirmation",
        		JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE) == 0)
        {
        	while(tab.getTabCount()>0)
        		tab.removeTab((tab.getTabAt(0)));//puis on les removes tous
            
        	window.dispose();//et on ferme la fenetre
        }	
	}
	
	public void windowActivated(WindowEvent arg0) {}
	public void windowClosed(WindowEvent arg0) {}
	public void windowDeactivated(WindowEvent arg0) {}
	public void windowDeiconified(WindowEvent arg0) {}
	public void windowIconified(WindowEvent arg0) {}
	public void windowOpened(WindowEvent arg0) {}

}
