package de.robadd.loginspector.reader.processor;

import de.robadd.loginspector.EventIndex;
import de.robadd.loginspector.reader.filter.MessageFilter;
import de.robadd.loginspector.reader.model.events.Event;

public class Indexer implements MessageProcessor
{
	@Override
	public void process(final Event msg)
	{
		EventIndex.getInstance().addEvent(msg);
	}

	@Override
	public void setMessageFilter(final MessageFilter msgFilter)
	{

	}
}
