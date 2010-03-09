package windows.actions.keylisteners;
 
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JTextArea;

import session.contact;
import socket.packet.handlers.sends.client_handlers.SendMsgTo_handler;
 
public class follow_keyboard implements KeyListener {
 
	private contact contact;
	private JTextArea text,box;
	 
	public follow_keyboard(contact ct, JTextArea toSend, JTextArea Inbox) {
		this.contact	= ct;
		this.text 		= toSend;
		this.box 		= Inbox;
	}
	 
	public void keyReleased(KeyEvent e) {}
	public void keyTyped(KeyEvent e) {}
	 
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER)
		{			
			box.setText(box.getText() + "J'ai écrit : " + text.getText().trim() + "\n");
			SendMsgTo_handler pck = new SendMsgTo_handler(text.getText().trim(),contact);
			pck.Send();
			text.setText("");
		}
	}
	 
}