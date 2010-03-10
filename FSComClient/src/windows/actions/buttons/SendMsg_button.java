package windows.actions.buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextArea;

import session.contact;
import socket.packet.handlers.Send_handler;
import socket.packet.handlers.sends.client_handlers.SendMsgTo_handler;

/*
 * Action sur le bouton d'envoi dans la fenetre de conversation
 */
public class SendMsg_button implements ActionListener {

	private contact contact;
	private JTextArea textarea;
	private JTextArea box;
	private String text;
	//on stocke les variables
	public SendMsg_button(contact ct, JTextArea textaenvoyer, JTextArea boxprincipal) {
		this.contact=ct;
		this.textarea=textaenvoyer;
		this.box=boxprincipal;
	}

	public void actionPerformed(ActionEvent e) 
	{
		text = textarea.getText();//on récupère se qui a été envoyé
		text = text.trim();// on supprimer les espaces/retours a la ligne superflu
		textarea.setText("\n");
		box.setText(box.getText()+"J'ai �crit : " + text + "\n");//on affiche dans la zone principale se que l'on a envoyé
		Send_handler pck = new SendMsgTo_handler(text,contact);//et on envoi le packet contenant le message au contact
		pck.Send();
	}
}

