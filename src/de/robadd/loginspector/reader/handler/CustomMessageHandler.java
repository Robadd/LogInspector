package de.robadd.loginspector.reader.handler;

import org.xml.sax.helpers.DefaultHandler;

import de.robadd.loginspector.reader.processor.MessageProcessor;

public abstract class CustomMessageHandler extends DefaultHandler
{
	public abstract void setProcessor(MessageProcessor processor);
}
