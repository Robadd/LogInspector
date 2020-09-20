package de.robadd.loginspector.reader.processor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import de.robadd.loginspector.reader.model.events.Event;

public class FileOutputProcessor extends FilterableMessageProcessor
{
	private FileOutputStream outStream;

	private FileOutputProcessor(final File outputFile) throws FileNotFoundException
	{
		super();
		outStream = new FileOutputStream(outputFile);
	}

	@Override
	public void process(final Event msg)
	{
		if (fitsCriteria(msg))
		{
			try
			{
				outStream.write(msg.toString().getBytes());
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}
}
