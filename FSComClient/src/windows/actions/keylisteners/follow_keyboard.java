package windows.actions.keylisteners;
 
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JTextArea;
import session.contact;
import socket.packet.handlers.sends.client_handlers.SendMsgTo_handler;

 /*
  * Action de surveillance de la touche entré dans les conversations
  */
public class follow_keyboard implements KeyListener {
 
	private contact contact;
	private JTextArea text,box;
	 
	public follow_keyboard(contact ct, JTextArea toSend, JTextArea Inbox) {
		//stockage des variables
		this.contact	= ct;
		this.text 		= toSend;
		this.box 		= Inbox;
	}
	 
	public void keyReleased(KeyEvent e) {}
	public void keyTyped(KeyEvent e) {}
	 
	public void keyPressed(KeyEvent e) {
		//lorsque la touche entré est pressé
		if (e.getKeyCode() == KeyEvent.VK_ENTER)
		{			
			//on affiche notre message dans la conversation
			box.setText(box.getText() + "J'ai �crit : " + text.getText().trim() + "\n");
			SendMsgTo_handler pck = new SendMsgTo_handler(text.getText().trim(),contact);//on creait le packet
			pck.Send();//et on envoi le packet
			text.setText("");//et on clean la box d'envoi
		}
	}
	 
}