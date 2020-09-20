package de.robadd.loginspector;

import java.awt.EventQueue;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import de.robadd.loginspector.ui.MainWindow;

public class Main
{
	private static final boolean ui = true;

	public static void main(final String[] args)
	{
		if (ui)
		{
			try
			{
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			}
			catch (ClassNotFoundException | InstantiationException | IllegalAccessException
					| UnsupportedLookAndFeelException e1)
			{
				e1.printStackTrace();
			}
			EventQueue.invokeLater(new Runnable()
			{
				@Override
				public void run()
				{
					try
					{

						MainWindow window = new MainWindow();
						window.setFrameVisible();
					}
					catch (Exception e)
					{
						e.printStackTrace();
					}
				}
			});
		}
	}

}
