package de.robadd.loginspector.reader.model;

public enum LogLevel
{
	ERROR, WARN, INFO, DEBUG, TRACE;

	public static LogLevel getByValue(final String val)
	{
		switch (val)
		{
			case "ERROR":
				return ERROR;
			case "WARN":
				return WARN;
			case "INFO":
				return INFO;
			case "DEBUG":
				return DEBUG;
			case "TRACE":
				return TRACE;
			default:
				return null;
		}
	}

	@Override
	public String toString()
	{
		return this.name();
	}
}
