package de.robadd.loginspector.reader.filter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import de.robadd.loginspector.reader.model.events.Event;

public class MessageFilterFactory
{
	private List<MessageFilter> filters = new ArrayList<>();

	public MessageFilterFactory()
	{

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

	public static MessageFilter packageFilter(final String packageName)
	{
		return new MessageFilter()
		{
			@Override
			public boolean fitsCriteria(final Event msg)
			{
				return msg.getClassName().startsWith("packageName");
			}
		};
	}
}
