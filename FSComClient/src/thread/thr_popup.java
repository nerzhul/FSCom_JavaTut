package thread;

import misc.Log;

public class thr_popup extends Thread{

	String _msg;
	boolean err;
	public thr_popup(String msg,boolean error)
	{
		_msg = msg;
		err = error;
	}
	
	public void run()
	{
		Log.ShowPopup(_msg, err);
	}
}
