package windows.actions.buttons;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import net.infonode.tabbedpanel.TabbedPanel;

public class ActionFermer implements WindowListener {
	
	private JFrame fenetre;
	private TabbedPanel onglet;
	public ActionFermer(JFrame frame, TabbedPanel tabPan) {
		fenetre=frame;
		onglet=tabPan;
	}

	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosed(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosing(WindowEvent arg0) {
	        int reponse = JOptionPane.showConfirmDialog(fenetre,"Vous allez fermer toutes vos fenêtres de conversation, êtes vous sur ?","Confirmation",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
	        if (reponse==0){
	        	int nbonglet = onglet.getTabCount();
	        	while(nbonglet>0){
	        		onglet.removeTab((onglet.getTabAt(0)));
	        		nbonglet = onglet.getTabCount();
	        	}
	                fenetre.dispose();
	        }	
	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

}
