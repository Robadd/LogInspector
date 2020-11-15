package de.robadd.loginspector.reader.model.events;

import java.util.Calendar;

import com.google.common.base.Objects;

import de.robadd.loginspector.reader.model.LogLevel;

public class ServerLogIndex implements Event
{
	private LogLevel logLevel;
	private Calendar time;
	private String className;
	private Integer line;
	private String threadName;

	public ServerLogIndex(final LogLevel logLevel, final Calendar time, final String className, final Integer line,
			final String threadName)
	{
		super();
		this.logLevel = logLevel;
		this.time = time;
		this.className = className;
		this.line = line;
		this.threadName = threadName;
	}

	/**
	 * @return the threadName
	 */
	@Override
	public String getThreadName()
	{
		return threadName;
	}

	/**
	 * @param threadName the threadName to set
	 */
	public void setThreadName(final String threadName)
	{
		this.threadName = threadName;
	}

	public ServerLogIndex()
	{
		super();
	}

	/**
	 * @return the logLevel
	 */
	@Override
	public LogLevel getLogLevel()
	{
		return logLevel;
	}

	/**
	 * @param logLevel the logLevel to set
	 */
	public void setLogLevel(final LogLevel logLevel)
	{
		this.logLevel = logLevel;
	}

	/**
	 * @return the time
	 */
	@Override
	public Calendar getTime()
	{
		return time;
	}

	/**
	 * @param time the time to set
	 */
	public void setTime(final Calendar time)
	{
		this.time = time;
	}

	/**
	 * @return the className
	 */
	@Override
	public String getClassName()
	{
		return className;
	}

	/**
	 * @param className the className to set
	 */
	@Override
	public void setClassName(final String className)
	{
		this.className = className;
	}

	/**
	 * @return the line
	 */
	public Integer getLine()
	{
		return line;
	}

	/**
	 * @param line the line to set
	 */
	public void setLine(final Integer line)
	{
		this.line = line;
	}

	@Override
	public boolean isBetween(final Calendar from, final Calendar to)
	{
		final boolean afterTrue = from == null || time.after(from);
		final boolean beforeTrue = to == null || time.before(to);
		return afterTrue && beforeTrue;
	}

	@Override
	public boolean isClassName(final String className)
	{
		return Objects.equal(this.className, className);
	}

	@Override
	public boolean isThreadName(final String threadName)
	{
		return Objects.equal(this.threadName, threadName);
	}

	@Override
	public String toString()
	{
		return "ServerLogIndex [logLevel=" + logLevel + ", time=" + time + ", className=" + className + ", line=" + line
				+ ", threadName=" + threadName + "]";
	}

	@Override
	public String getStringRepresentation()
	{
		return toString();
	}

	@Override
	public void setLogLevel(final String logLevel)
	{
		this.logLevel = LogLevel.getByValue(logLevel);
	}

	@Override
	public boolean isLogLevel(final String logLevel)
	{
		return this.logLevel.equals(LogLevel.getByValue(logLevel));
	}

}
