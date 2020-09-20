package de.robadd.loginspector.reader.filter;

import de.robadd.loginspector.reader.model.events.Event;

public interface MessageFilter
{
	public boolean fitsCriteria(Event msg);
}
