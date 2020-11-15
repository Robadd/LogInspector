package de.robadd.loginspector.ui.component;

import java.util.Calendar;

import de.robadd.loginspector.reader.filter.MessageFilterFactory.Type;
import de.robadd.loginspector.reader.model.LogLevel;

public class FilterComponentFactory
{
	public static FilterComponent<String> classNameComponent()
	{
		final FilterComponent<String> retVal = new FilterComponent<String>("Klasse", Type.CLASS)
		{
			private static final long serialVersionUID = 1L;
		};
		return retVal;
	}

	public static FilterComponent<String> threadNameComponent()
	{
		final FilterComponent<String> retVal = new FilterComponent<String>("Thread", Type.THREAD)
		{
			private static final long serialVersionUID = 1L;
		};
		return retVal;
	}

	public static FilterComponent<Calendar> timeRangeComponent()
	{
		final FilterComponent<Calendar> retVal = new FilterComponent<Calendar>("Zeit", Type.TIME)
		{
			private static final long serialVersionUID = 1L;
		};
		return retVal;
	}

	public static FilterComponent<LogLevel> logLevelComponent()
	{
		final FilterComponent<LogLevel> retVal = new FilterComponent<LogLevel>("Log Level", Type.LOGLEVEL)
		{
			private static final long serialVersionUID = 1757417995001162132L;
		};
		return retVal;
	}

	public static FilterComponent<String> ebayApiComponent()
	{
		final FilterComponent<String> retVal = new FilterComponent<String>("API", Type.API)
		{
			private static final long serialVersionUID = 1L;
		};
		return retVal;
	}
}
