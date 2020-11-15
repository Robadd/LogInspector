package de.robadd.loginspector.reader.processor;

import java.awt.EventQueue;

import javax.swing.JTextArea;

import de.robadd.loginspector.reader.model.events.Event;

public class UiOutputProcessor extends FilterableMessageProcessor
{
	private JTextArea pane;

	@Override
	public void process(final Event msg)
	{
		final String event = msg.getStringRepresentation();
		EventQueue.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{
				pane.append(event);
				pane.append("\n");
			}
		});
	}

	public void setOuputTextArea(final JTextArea pane)
	{
		this.pane = pane;
	}
}
