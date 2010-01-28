package windows;

import javax.swing.SwingUtilities;

import windows.forms.form_connect;
import windows.forms.form_contact;

public class windowthread extends Thread{

	private static form_connect fmconn;
	private static form_contact fmcontact;
	public windowthread(){}
	
	public void run()
	{
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				setFmConn(new form_connect());
			}
		});
	}

	public static void setFmConn(form_connect fmc) { fmconn = fmc; }
	public static form_connect getFmConn() { return fmconn; }
	public static void setFmContact(form_contact fmcont) { fmcontact = fmcont; }
	public static form_contact getFmContact() { return fmcontact; }
}
