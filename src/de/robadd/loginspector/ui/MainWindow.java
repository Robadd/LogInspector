package de.robadd.loginspector.ui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.datatransfer.DataFlavor;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;

import de.robadd.loginspector.Main;
import de.robadd.loginspector.loader.InterfaceLoader;
import de.robadd.loginspector.reader.LogFileReader;
import de.robadd.loginspector.reader.handler.CustomMessageHandler;
import de.robadd.loginspector.reader.handler.MessageHandler;
import de.robadd.loginspector.reader.handler.MessageIndexHandler;
import de.robadd.loginspector.reader.model.events.Event;
import de.robadd.loginspector.reader.processor.Indexer;
import de.robadd.loginspector.reader.processor.UiOutputProcessor;
import de.robadd.loginspector.ui.component.FilterComponent;
import de.robadd.loginspector.ui.component.ServerlogSettingsPanel;

public class MainWindow
{
	private JFrame frame;
	private Integer width = 500;
	private Integer height = 500;
	private File file;
	private ServerlogSettingsPanel props;
	private JTextArea textPane;
	private JComponent splitPane;

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
		splitPane = new JPanel();
		if (Main.output)
		{
			splitPane = new JSplitPane();
		}
		props = new ServerlogSettingsPanel(2);

		frame.setContentPane(splitPane);
		frame.setBounds(100, 100, width, height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		props.setDropTarget(getDopTarget());
		props.getLoadButton().addActionListener(loadFileListener());
		props.getOpenButton().addActionListener(openFileListener());

		textPane = new JTextArea();
		InterfaceLoader.getSubTypes(Event.class).stream().forEach(System.out::println);
		System.out.println();
		InterfaceLoader.getSubTypes(FilterComponent.class).stream().forEach(System.out::println);

		final Dimension minimumSize = Main.output ? new Dimension(width / 2, height) : new Dimension(width, height);
		textPane.setMinimumSize(minimumSize);

		if (Main.output)
		{
			((JSplitPane) splitPane).setOneTouchExpandable(true);
			((JSplitPane) splitPane).setDividerLocation(-1);
			((JSplitPane) splitPane).setRightComponent(textPane);
			((JSplitPane) splitPane).setLeftComponent(props);
		}
		else
		{
			splitPane.setLayout(new GridLayout());
			props.setMinimumSize(new Dimension(width, height));
			splitPane.add(props);
		}
	}

	private ActionListener openFileListener()
	{
		return new ActionListener()
		{
			@Override
			public void actionPerformed(final ActionEvent e)
			{
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setCurrentDirectory(new File("C:\\programming\\eclipse2"));
				int result = fileChooser.showOpenDialog(frame);
				if (result == JFileChooser.APPROVE_OPTION)
				{
					file = fileChooser.getSelectedFile();
					new LogFileReader.Builder()
							.setMessageHandler(new MessageIndexHandler())
							.setProcessor(new Indexer())
							.build()
							.read(file);
					props.fillFilterBoxes();
					System.out.print(true);
				}
			}
		};
	}

	private DropTarget getDopTarget()
	{
		return new DropTarget()
		{
			private static final long serialVersionUID = -5655403058080630157L;

			@SuppressWarnings("unchecked")
			@Override
			public synchronized void drop(final DropTargetDropEvent evt)
			{
				try
				{
					evt.acceptDrop(DnDConstants.ACTION_COPY);
					List<File> droppedFiles = (List<File>) evt
							.getTransferable()
							.getTransferData(DataFlavor.javaFileListFlavor);
					for (File file : droppedFiles)
					{
						new LogFileReader.Builder()
								.setMessageHandler(new MessageIndexHandler())
								.setProcessor(new Indexer())
								.build()
								.read(file);
						props.fillFilterBoxes();
					}
				}
				catch (Exception ex)
				{
					ex.printStackTrace();
				}
			}
		};
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
					UiOutputProcessor processor = new UiOutputProcessor();
					processor.setOuputTextArea(textPane);
					LogFileReader reader = new LogFileReader.Builder()
							.setMessageHandler(handler)
							.setProcessor(processor)
							.build();
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

	public ServerlogSettingsPanel getProps()
	{
		return props;
	}
}
