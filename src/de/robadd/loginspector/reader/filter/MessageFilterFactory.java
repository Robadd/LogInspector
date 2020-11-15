package de.robadd.loginspector.reader.filter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import de.robadd.loginspector.reader.model.events.Event;

public class MessageFilterFactory
{
	private List<MessageFilter> filters = new ArrayList<>();

	public enum Type
	{
		CLASS, TIME, THREAD, LOGLEVEL, API;
	}

	public MessageFilterFactory()
	{

	}

	public static MessageFilter of(final List<MessageFilter> additional)
	{
		return new MessageFilter()
		{
			@Override
			public boolean fitsCriteria(final Event msg)
			{
				return additional.parallelStream().allMatch(m -> m.fitsCriteria(msg));
			}
		};
	}

	public MessageFilter build()
	{
		return new MessageFilter()
		{
			@Override
			public boolean fitsCriteria(final Event msg)
			{
				return filters.parallelStream().allMatch(m -> m.fitsCriteria(msg));
			}
		};
	}

	public static MessageFilter timeFilter(final Calendar from, final Calendar to)
	{
		return new MessageFilter()
		{
			@Override
			public boolean fitsCriteria(final Event msg)
			{
				return msg.isBetween(from, to);
			}
		};
	}

	public static MessageFilter classFilter(final String className)
	{
		return new MessageFilter()
		{
			@Override
			public boolean fitsCriteria(final Event msg)
			{
				return msg.isClassName(className);
			}
		};
	}

	public static MessageFilter classFilter(final List<String> className)
	{
		return new MessageFilter()
		{
			@Override
			public boolean fitsCriteria(final Event msg)
			{
				return className.parallelStream().anyMatch(msg::isClassName);
			}
		};
	}

	public static MessageFilter threadFilter(final String value)
	{
		return new MessageFilter()
		{
			@Override
			public boolean fitsCriteria(final Event msg)
			{
				return msg.isThreadName(value);
			}
		};
	}

	/**
	 * @param values the threads
	 * @return filter for thread names
	 */
	public static MessageFilter threadFilter(final List<String> values)
	{
		return new MessageFilter()
		{
			@Override
			public boolean fitsCriteria(final Event msg)
			{
				return values.parallelStream().anyMatch(msg::isThreadName);
			}
		};
	}

	/**
	 * @return An always true filter
	 */
	public static MessageFilter dud()
	{
		return new MessageFilter()
		{

			@Override
			public boolean fitsCriteria(final Event msg)
			{
				return true;
			}
		};
	}

	public static MessageFilter logLevelFilter(final String className)
	{
		return new MessageFilter()
		{
			@Override
			public boolean fitsCriteria(final Event msg)
			{
				return msg.isLogLevel(className);
			}
		};
	}

	public static MessageFilter logLevelFilter(final List<String> className)
	{
		return new MessageFilter()
		{
			@Override
			public boolean fitsCriteria(final Event msg)
			{
				return className.parallelStream().anyMatch(msg::isLogLevel);
			}
		};
	}

}
