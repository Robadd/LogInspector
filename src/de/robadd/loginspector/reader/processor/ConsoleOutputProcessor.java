package de.robadd.loginspector.reader.processor;

import de.robadd.loginspector.reader.model.events.Event;

public class ConsoleOutputProcessor extends FilterableMessageProcessor
{
	@Override
	public void process(final Event msg)
	{
		if (fitsCriteria(msg))
		{
			System.out.println(msg);
			System.out.println((Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / 1024 / 1024);
		}
	}
}
