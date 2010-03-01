package windows.forms.buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

public class cancel_inscription_button implements ActionListener {

	private JFrame frame2;
	public cancel_inscription_button(JFrame frame) {
		frame2=frame;
	}

	public void actionPerformed(ActionEvent e) {
		frame2.dispose();
	}

}
