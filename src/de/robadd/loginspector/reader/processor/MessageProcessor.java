package de.robadd.loginspector.reader.processor;

import de.robadd.loginspector.reader.filter.MessageFilter;
import de.robadd.loginspector.reader.model.events.Event;

public interface MessageProcessor
{
	public void process(Event msg);

	public void setMessageFilter(MessageFilter msgFilter);
}
