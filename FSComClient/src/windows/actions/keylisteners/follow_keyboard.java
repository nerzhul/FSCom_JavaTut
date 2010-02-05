package windows.actions.keylisteners;
 
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
 
import javax.swing.JTextArea;

import session.contact;

import windows.actions.buttons.sendmsg_button;
 
public class follow_keyboard implements KeyListener {
 
	private contact contact;
	private JTextArea text,box;
	 
	public follow_keyboard(contact ct, JTextArea textaenvoyer, JTextArea textprincipal) {
		this.contact=ct;
		this.text=textaenvoyer;
		this.box=textprincipal;
	}
	 
	public void keyReleased(KeyEvent e) {
		
	}
	 
	public void keyTyped(KeyEvent e) {}
	 
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER)
		{
			sendmsg_button smb = new sendmsg_button(contact,text,box);
			smb.SendMsg();
		}
	}
	 
}