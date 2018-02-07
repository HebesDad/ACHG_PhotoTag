package org.achg.phototag.views;

import javax.annotation.PostConstruct;

import org.eclipse.e4.ui.di.UISynchronize;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

/**
 * A very basic Console View
 * 
 * @author cdr
 *
 */
public class ConsoleView
{
	private UISynchronize _sync;
	private Text _consoleText;

	/**
	 * method to create the gadgets
	 * 
	 * @param viewParent the parent
	 * @param sync the UI synchroniser
	 */
	@PostConstruct
	public void create(Composite viewParent, UISynchronize sync)
	{
		_sync = sync;
		GridLayout layout = new GridLayout(1, false);
		viewParent.setLayout(layout);
		Label lab = new Label(viewParent, SWT.NONE);
		lab.setText("Console:");
		_consoleText = new Text(viewParent, SWT.MULTI | SWT.WRAP | SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);
		_consoleText.setEnabled(false);
		GridData gd = new GridData(SWT.FILL, SWT.FILL, true, true);
		_consoleText.setLayoutData(gd);
	}

	/**
	 * Add a message to the console
	 * 
	 * @param message the message (no need to add linebreaks)
	 */
	public void addMessage(String message)
	{
		_sync.asyncExec(new Runnable()
		{

			@Override
			public void run()
			{
				String content = _consoleText.getText();
				content = content + message + " \r\n";
				_consoleText.setText(content);
				_consoleText.setSelection(content.length() - 2);
			}
		});
	}

}
