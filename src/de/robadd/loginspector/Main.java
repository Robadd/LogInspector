package de.robadd.loginspector;

import java.awt.EventQueue;
import java.util.Arrays;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import de.robadd.loginspector.ui.MainWindow;

public class Main
{
	public static boolean ui = true;
	public static boolean output = false;

	public static void main(final String[] args)
	{
		if (Arrays.asList(args).size() > 0)
		{
			ui = false;
		}
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
