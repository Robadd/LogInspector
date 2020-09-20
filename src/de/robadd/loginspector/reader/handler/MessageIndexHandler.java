package de.robadd.loginspector.reader.handler;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import de.robadd.loginspector.reader.model.LogLevel;
import de.robadd.loginspector.reader.model.events.ServerLogIndex;
import de.robadd.loginspector.reader.processor.MessageProcessor;
import de.robadd.loginspector.utils.DateUtils;

public class MessageIndexHandler extends DefaultHandler
{
	private final MessageProcessor processor;
	private ServerLogIndex msg;

	public MessageIndexHandler(final MessageProcessor processor)
	{
		this.processor = processor;
	}

	@Override
	public void startElement(final String uri, final String localName, final String qName, final Attributes attributes)
			throws SAXException
	{

		if (qName.equals("event"))
		{
			msg = new ServerLogIndex();
			msg.setLogLevel(LogLevel.getByValue(attributes.getValue("level")));
			msg.setThreadName(attributes.getValue("thread"));
			msg.setTime(DateUtils.fromString(attributes.getValue("timestamp")));
		}
		else if (qName.equals("locationInfo"))
		{
			msg.setClassName(attributes.getValue("class"));
		}
	}

	@Override
	public void endElement(final String uri, final String localName, final String qName) throws SAXException
	{
		if (qName.equals("event"))
		{
			processor.process(msg);
			msg = null;
		}
	}
}
