package de.robadd.loginspector.reader.model.events;

import java.util.Calendar;

import de.robadd.loginspector.reader.model.LogLevel;

public interface Event
{
	public boolean isBetween(final Calendar from, final Calendar to);

	public boolean isClassName(final String className);

	public boolean isThreadName(final String threadName);

	public String getClassName();

	public void setClassName(final String className);

	public String getStringRepresentation();

	public String getThreadName();

	public LogLevel getLogLevel();

	public Calendar getTime();

	public void setLogLevel(final String logLevel);

	public boolean isLogLevel(final String logLevel);

}
