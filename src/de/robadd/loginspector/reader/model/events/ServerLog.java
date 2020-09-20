package de.robadd.loginspector.reader.model.events;

import java.util.Calendar;

import com.google.common.base.Objects;

import de.robadd.loginspector.reader.model.LogLevel;

public class ServerLog implements Event
{
	private String logger;
	private String thread;
	private Calendar time;
	private LogLevel level;
	private String message;
	private String clazz;
	private String method;
	private String file;
	private Integer line;

	/**
	 * @return the logger
	 */
	public String getLogger()
	{
		return logger;
	}

	/**
	 * @param logger the logger to set
	 */
	public void setLogger(final String logger)
	{
		this.logger = logger;
	}

	/**
	 * @return the thread
	 */
	public String getThread()
	{
		return thread;
	}

	/**
	 * @param thread the thread to set
	 */
	public void setThread(final String thread)
	{
		this.thread = thread;
	}

	/**
	 * @return the time
	 */
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
	 * @return the level
	 */
	public LogLevel getLevel()
	{
		return level;
	}

	/**
	 * @param level the level to set
	 */
	public void setLevel(final LogLevel level)
	{
		this.level = level;
	}

	/**
	 * @return the message
	 */
	public String getMessage()
	{
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(final String message)
	{
		this.message = message;
	}

	/**
	 * @return the clazz
	 */
	public String getClazz()
	{
		return clazz;
	}

	/**
	 * @param clazz the clazz to set
	 */
	public void setClazz(final String clazz)
	{
		this.clazz = clazz;
	}

	/**
	 * @return the method
	 */
	public String getMethod()
	{
		return method;
	}

	/**
	 * @param method the method to set
	 */
	public void setMethod(final String method)
	{
		this.method = method;
	}

	/**
	 * @return the file
	 */
	public String getFile()
	{
		return file;
	}

	/**
	 * @param file the file to set
	 */
	public void setFile(final String file)
	{
		this.file = file;
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
		return Objects.equal(this.clazz, className);
	}

	@Override
	public boolean isThreadName(final String threadName)
	{
		return Objects.equal(this.thread, threadName);
	}

	@Override
	public String getClassName()
	{
		return getClazz();
	}

	@Override
	public void setClassName(final String className)
	{
		setClazz(className);
	}

	@Override
	public String toString()
	{
		return super.toString();
//		"<event logger=\"%s\" timestamp=\"17.08.2020 0:00:00\" level=\"%s\" thread=\"%s\">\r\n" +
//		"		<message><![CDATA[%s]]></message>\r\n" +
//		"		<locationInfo class=\"%s\" method=\"%s\" file=\"%s\" line=\"%i\"/>\r\n" +
//		"		</event>"
	}

	@Override
	public String getStringRepresentation()
	{
		return toString();
	}
}
