package de.robadd.loginspector;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import de.robadd.loginspector.reader.model.LogLevel;
import de.robadd.loginspector.reader.model.events.Event;

public class EventIndex
{
	private static final EventIndex INSTANCE = new EventIndex();
	private final Set<String> classNames = new HashSet<>();
	private final Set<String> threadNames = new HashSet<>();
	private final Set<LogLevel> logLevels = new HashSet<>();
	private Calendar begin;
	private Calendar end;

	private EventIndex()
	{
	}

	public static EventIndex getInstance()
	{
		return INSTANCE;
	};

	public void addEvent(final Event msg)
	{
		classNames.add(msg.getClassName());
		threadNames.add(msg.getThreadName());
		logLevels.add(msg.getLogLevel());
		final Calendar time = msg.getTime();
		if (begin == null || time.before(begin))
		{
			begin = time;
		}
		if (end == null || time.after(end))
		{
			end = time;
		}
	}

	/**
	 * @return the classNames
	 */
	public Set<String> getClassNames()
	{
		return classNames;
	}

	/**
	 * @return the threadNames
	 */
	public Set<String> getThreadNames()
	{
		return threadNames;
	}

	/**
	 * @return the logLevels
	 */
	public Set<LogLevel> getLogLevels()
	{
		return logLevels;
	}

	/**
	 * @return the begin
	 */
	public Calendar getBegin()
	{
		return begin;
	}

	/**
	 * @return the end
	 */
	public Calendar getEnd()
	{
		return end;
	}

	public void clear()
	{
		classNames.clear();
		threadNames.clear();
		logLevels.clear();
		begin = null;
		end = null;
	}
}
