package windows.actions.buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextArea;

public class Retablir_button implements ActionListener {

	private JTextArea envoi;
	public Retablir_button(JTextArea txt) {
		envoi=txt;	
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		envoi.setText("\n");
	}

}
