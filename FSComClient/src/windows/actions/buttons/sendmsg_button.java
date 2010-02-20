package windows.actions.buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextArea;

import session.contact;
import socket.packet.handlers.send_handler;
import socket.packet.handlers.sends.SendMsgTo_handler;

public class sendmsg_button implements ActionListener {

	private contact contact;
	private JTextArea textarea;
	private JTextArea box;
	private String text;
	public sendmsg_button(contact ct, JTextArea textaenvoyer, JTextArea boxprincipal) {
		this.contact=ct;
		this.textarea=textaenvoyer;
		this.box=boxprincipal;
	}


	public void actionPerformed(ActionEvent e) 
	{
		SendMsg();
	}
	
	public void SendMsg()
	{
		text = textarea.getText();
		text = text.trim();
		textarea.setText("\n");
		box.setText(box.getText()+"J'ai �crit : " + text + "\n");
		send_handler pck = new SendMsgTo_handler(text,contact);
		pck.Send();
	}
}
