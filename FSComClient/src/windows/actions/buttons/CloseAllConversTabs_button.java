package windows.actions.buttons;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import net.infonode.tabbedpanel.TabbedPanel;

public class CloseAllConversTabs_button implements WindowListener {
	
	private JFrame window;
	private TabbedPanel tab;
	public CloseAllConversTabs_button(JFrame frame, TabbedPanel tabPan) {
		window=frame;
		tab=tabPan;
	}

	public void windowActivated(WindowEvent arg0) {	}
	public void windowClosed(WindowEvent arg0) {	}

	public void windowClosing(WindowEvent arg0) 
	{
        int reponse = JOptionPane.showConfirmDialog(window,"Vous allez fermer toutes vos fenêtres de conversation, êtes vous sur ?","Confirmation",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
        if (reponse==0)
        {
        	int nbonglet = tab.getTabCount();
        	while(nbonglet>0){
        		tab.removeTab((tab.getTabAt(0)));
        		nbonglet = tab.getTabCount();
        	}
                window.dispose();
        }	
	}

	public void windowDeactivated(WindowEvent arg0) {}
	public void windowDeiconified(WindowEvent arg0) {}
	public void windowIconified(WindowEvent arg0) {}
	public void windowOpened(WindowEvent arg0) {}

}
