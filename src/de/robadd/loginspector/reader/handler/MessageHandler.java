package de.robadd.loginspector.reader.handler;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import de.robadd.loginspector.reader.model.LogLevel;
import de.robadd.loginspector.reader.model.events.ServerLog;
import de.robadd.loginspector.reader.processor.MessageProcessor;
import de.robadd.loginspector.utils.DateUtils;

public class MessageHandler extends CustomMessageHandler
{
	private MessageProcessor processor;
	private ServerLog msg;
	private boolean elementMessage = false;

	public MessageHandler()
	{
	}

	@Override
	public void startElement(final String uri, final String localName, final String qName, final Attributes attributes)
			throws SAXException
	{
		if (qName.equals("event"))
		{
			msg = new ServerLog();
			msg.setLogLevel(LogLevel.getByValue(attributes.getValue("level")));
			msg.setThreadName(attributes.getValue("thread"));
			msg.setTime(DateUtils.fromString(attributes.getValue("timestamp")));
			msg.setLogger(attributes.getValue("logger"));
		}
		else if (qName.equals("message"))
		{
			elementMessage = true;
		}
		else if (qName.equals("locationInfo"))
		{
			msg.setClassName(attributes.getValue("class"));
			msg.setMethod(attributes.getValue("method"));
			msg.setFile(attributes.getValue("file"));
			try
			{
				msg.setLine(Integer.valueOf(attributes.getValue("line")));
			}
			catch (NumberFormatException e)
			{
			}
		}
	}

	@Override
	public void characters(final char[] ch, final int start, final int length) throws SAXException
	{
		if (elementMessage)
		{
			msg.setMessage(new String(ch));
		}
	}

	@Override
	public void endElement(final String uri, final String localName, final String qName) throws SAXException
	{
		if (qName.equals("message"))
		{
			elementMessage = false;
		}
		else if (qName.equals("event"))
		{
			processor.process(msg);
			msg = null;
		}
	}

	@Override
	public void setProcessor(final MessageProcessor processor)
	{
		this.processor = processor;
	}
}
