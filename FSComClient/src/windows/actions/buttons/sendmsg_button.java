package windows.actions.buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextArea;

import session.objects.contact;

public class sendmsg_button implements ActionListener {

	private contact contact;
	private JTextArea textarea;
	private JTextArea box;
	private String text;
	public sendmsg_button(contact interlocuteur, JTextArea textaenvoyer, JTextArea boxprincipal) {
		this.contact=interlocuteur;
		this.textarea=textaenvoyer;
		this.box=boxprincipal;
	}


	public void actionPerformed(ActionEvent e) {
		this.text = textarea.getText();
		textarea.setText("");
		box.setText(box.getText()+"J'ai écrit : "+text);
	}
}

