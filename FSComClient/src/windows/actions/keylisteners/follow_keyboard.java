package windows.actions.keylisteners;
 
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JTextArea;

import session.contact;
import socket.packet.handlers.Send_handler;
import socket.packet.handlers.sends.SendMsgTo_handler;
 
public class follow_keyboard implements KeyListener {
 
	private contact contact;
	private JTextArea textenvoy;
	private JTextArea textrecu;
	private String text;
	 
	public follow_keyboard(contact ct, JTextArea textaenvoyer, JTextArea textprincipal) {
		this.contact=ct;
		this.textenvoy=textaenvoyer;
		this.textrecu=textprincipal;
	}
	 
	public void keyReleased(KeyEvent e) {
		
	}
	 
	public void keyTyped(KeyEvent e) {}
	 
	public void keyPressed(KeyEvent e) {
		
		if (e.getKeyCode() == KeyEvent.VK_ENTER)
		{
			text = textenvoy.getText();
			textenvoy.setText("");
			text = text.trim();
			textrecu.setText(textrecu.getText()+"J'ai écrit : " + text + "\n");
			Send_handler pck = new SendMsgTo_handler(text,contact);
			pck.Send();
		}
	}
	 
}