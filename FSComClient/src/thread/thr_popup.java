package thread;

import misc.Log;

public class thr_popup extends Thread{

	String _msg,titl;
	boolean err;
	public thr_popup(String msg,String title,boolean error)
	{
		_msg = msg;
		err = error;
		titl = title;
	}
	
	public void run()
	{
		Log.ShowPopup(_msg,titl,err);
	}
}
