package de.robadd.loginspector.ui;

import java.awt.Dimension;
import java.awt.datatransfer.DataFlavor;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JSplitPane;
import javax.swing.JTextPane;

import de.robadd.loginspector.reader.LogFileReader;
import de.robadd.loginspector.reader.handler.CustomMessageHandler;
import de.robadd.loginspector.reader.handler.MessageHandler;
import de.robadd.loginspector.reader.processor.MessageProcessor;
import de.robadd.loginspector.reader.processor.UiOutputProcessor;
import de.robadd.loginspector.ui.component.SearchSettingsPanel;

public class MainWindow
{
	private JFrame frame;
	private Integer width = 1223;
	private Integer height = 514;
	private File file;
	private SearchSettingsPanel props;

	/**
	 * Create the application.
	 */
	public MainWindow()
	{
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize()
	{
		frame = new JFrame();
		final JSplitPane splitPane = new JSplitPane();
		props = new SearchSettingsPanel();

		frame.setContentPane(splitPane);
		frame.setBounds(100, 100, width, height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		initDropZone();
		props.getLoadButton().addActionListener(loadFileListener());
		splitPane.setOneTouchExpandable(true);
		splitPane.setDividerLocation(-1);

		final JTextPane textPane = new JTextPane();

		final Dimension minimumSize = new Dimension(300, 50);
		textPane.setMinimumSize(minimumSize);

		splitPane.setRightComponent(textPane);
		splitPane.setLeftComponent(props);
	}

	private void initDropZone()
	{
		props.setDropTarget(new DropTarget()
		{
			private static final long serialVersionUID = -5655403058080630157L;

			@SuppressWarnings("unchecked")
			@Override
			public synchronized void drop(final DropTargetDropEvent evt)
			{
				try
				{
					evt.acceptDrop(DnDConstants.ACTION_COPY);
					List<File> droppedFiles = (List<File>) evt.getTransferable()
							.getTransferData(DataFlavor.javaFileListFlavor);
					for (File file : droppedFiles)
					{
						setFile(file);
						System.out.println(file.getAbsolutePath());
					}
				}
				catch (Exception ex)
				{
					ex.printStackTrace();
				}
			}
		});
	}

	private ActionListener loadFileListener()
	{
		return new ActionListener()
		{
			@Override
			public void actionPerformed(final ActionEvent e)
			{
				if (file != null)
				{
					CustomMessageHandler handler = new MessageHandler();
					MessageProcessor processor = new UiOutputProcessor();
					LogFileReader reader = new LogFileReader.Builder().setMessageHandler(handler)
							.setProcessor(processor).build();
					reader.read(file);
				}
			}
		};
	}

	public void setFrameVisible()
	{
		this.frame.setVisible(true);
	}

	/**
	 * @return the file
	 */
	public File getFile()
	{
		return file;
	}

	/**
	 * @param file the file to set
	 */
	public void setFile(final File file)
	{
		this.file = file;
	}

	public SearchSettingsPanel getProps()
	{
		return props;
	}
}
