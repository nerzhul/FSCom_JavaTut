package windows;

import javax.swing.SwingUtilities;

public class windowthread extends Thread{

	public windowthread()
	{
		
	}
	
	public void run()
	{
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				//new form_connect();
			}
		});
	}
}
