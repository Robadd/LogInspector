package de.robadd.loginspector.reader.model.events;

import java.util.Calendar;

public interface Event
{
	public boolean isBetween(final Calendar from, final Calendar to);

	public boolean isClassName(final String className);

	public boolean isThreadName(final String threadName);

	public String getClassName();

	public void setClassName(String className);

	public String getStringRepresentation();
}
