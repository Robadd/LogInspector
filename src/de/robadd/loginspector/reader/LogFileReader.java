package de.robadd.loginspector.reader;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.SequenceInputStream;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Collections;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

import de.robadd.loginspector.reader.filter.MessageFilter;
import de.robadd.loginspector.reader.handler.CustomMessageHandler;
import de.robadd.loginspector.reader.processor.MessageProcessor;

public class LogFileReader
{
	private CustomMessageHandler messageHandler;

	private LogFileReader()
	{
		super();
	}

	public void read(final File file)
	{
		try
		{
			Path path = file.toPath();
			SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
			final InputStream wellFormedXml = new SequenceInputStream(
					Collections.enumeration(Arrays.asList(new InputStream[]
					{ new ByteArrayInputStream("<dummy>".getBytes()), new FileInputStream(path.toFile()),
							new ByteArrayInputStream("</dummy>".getBytes()), })));
			parser.parse(wellFormedXml, messageHandler);
		}
		catch (ParserConfigurationException | SAXException | IOException e)
		{
			e.printStackTrace();
		}
	}

	private LogFileReader(final CustomMessageHandler messageHandler)
	{
		super();
		this.messageHandler = messageHandler;
	}

	public static class Builder
	{
		private MessageProcessor processor;
		private CustomMessageHandler messageHandler;
		private MessageFilter filter;

		/**
		 * @param processor the processor to set
		 */
		public Builder setProcessor(final MessageProcessor processor)
		{
			this.processor = processor;
			return this;
		}

		/**
		 * @param messageHandler the messageHandler to set
		 */
		public Builder setMessageHandler(final CustomMessageHandler messageHandler)
		{
			this.messageHandler = messageHandler;
			return this;
		}

		/**
		 * @param filter the filter to set
		 */
		public Builder setFilter(final MessageFilter filter)
		{
			this.filter = filter;
			return this;
		}

		public LogFileReader build()
		{
			if (filter != null)
			{
				processor.setMessageFilter(filter);
			}
			messageHandler.setProcessor(processor);
			return new LogFileReader(messageHandler);
		}
	}
}
