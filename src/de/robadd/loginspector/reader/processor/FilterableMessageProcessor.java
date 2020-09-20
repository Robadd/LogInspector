package de.robadd.loginspector.reader.processor;

import de.robadd.loginspector.reader.filter.MessageFilter;
import de.robadd.loginspector.reader.model.events.Event;

public abstract class FilterableMessageProcessor implements MessageProcessor
{
	private MessageFilter msgFilter;

	@Override
	public void setMessageFilter(final MessageFilter msgFilter)
	{
		this.msgFilter = msgFilter;
	}

	protected boolean fitsCriteria(final Event msg)
	{
		return msgFilter == null || msgFilter.fitsCriteria(msg);
	}
}
