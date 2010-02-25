package thread;

import javax.swing.SwingUtilities;

import windows.forms.form_master;

public class windowthread extends Thread{

	private static form_master form;
	public windowthread(){}
	
	public void run()
	{
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				setFmConn(new form_master());
			}
		});
	}

	public static void setFmConn(form_master fmc) { form = fmc; }
	public static form_master getFmConn() { return form; }
	public static void SwitchPanel(int i) { form.BuildPanel(i); }
	
}
