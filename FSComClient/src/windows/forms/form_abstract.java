package windows.forms;

import javax.swing.JFrame;

public abstract class form_abstract extends JFrame{

	/**
	 * 
	 */
	protected static final long serialVersionUID = 1L;

	protected abstract void BuildWindow();
	protected abstract void BuildMenuBar();
}
