package windows.actions.buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextArea;

/*
 * Action sur le click sur le bouton rétablir dans la fenetre de conversation
 */
public class Retablir_button implements ActionListener {

	private JTextArea envoi;
	public Retablir_button(JTextArea txt) {
		envoi=txt;	
	}

	//on efface ce que l'on avait écrit
	public void actionPerformed(ActionEvent arg0) {
		envoi.setText("\n");
	}

}
