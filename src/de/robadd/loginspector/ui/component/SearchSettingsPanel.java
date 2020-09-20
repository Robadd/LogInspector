package de.robadd.loginspector.ui.component;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.AbstractListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

import de.robadd.loginspector.reader.model.LogLevel;

public class SearchSettingsPanel extends JPanel
{
	private JTextField fromTextField;
	private static final long serialVersionUID = 5634933344406960190L;
	private JTextField toTextField;
	private JTextField classTextField;
	private JTextField threadTextField;
	private JPanel fileChooserPanel;
	private JTextField filedTextField;
	private JButton loadButton;

	public SearchSettingsPanel()
	{
		setLayout(new FormLayout(new ColumnSpec[]
		{ ColumnSpec.decode("1px"), FormSpecs.UNRELATED_GAP_COLSPEC, ColumnSpec.decode("left:default:grow"),
				FormSpecs.UNRELATED_GAP_COLSPEC, }, new RowSpec[]
		{ FormSpecs.UNRELATED_GAP_ROWSPEC, RowSpec.decode("top:default"), FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("top:default"), FormSpecs.RELATED_GAP_ROWSPEC, RowSpec.decode("top:default"),
				FormSpecs.UNRELATED_GAP_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC, RowSpec.decode("default:grow"), }));

		JPanel logLevelPane = new JPanel();
		logLevelPane.setToolTipText("Log Level");
		logLevelPane.setBorder(new TitledBorder(null, "Log Level", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		add(logLevelPane, "3, 2, fill, fill");
		logLevelPane.setLayout(new GridLayout(0, 1, 0, 0));

		JList<String> list = new JList<>();
		list.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		list.setModel(new AbstractListModel<String>()
		{
			private static final long serialVersionUID = -6636982494260446250L;
			LogLevel[] values = LogLevel.values();

			@Override
			public int getSize()
			{
				return values.length;
			}

			@Override
			public String getElementAt(final int index)
			{
				return values[index].name();
			}
		});
		logLevelPane.add(list);

		JPanel timePane = new JPanel();
		add(timePane, "3, 4, fill, fill");

		timePane.setBorder(new TitledBorder(null, "Zeitraum", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		timePane.setLayout(new FormLayout(new ColumnSpec[]
		{ FormSpecs.UNRELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"), FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.UNRELATED_GAP_COLSPEC, }, new RowSpec[]
		{ FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, }));

		JLabel fromLabel = new JLabel("Von:");
		timePane.add(fromLabel, "2, 2");

		JSlider fromTime = new JSlider();
		timePane.add(fromTime, "4, 2");

		fromTextField = new JTextField();
		timePane.add(fromTextField, "6, 2, fill, default");
		fromTextField.setColumns(10);

		JLabel ToLabel = new JLabel("Bis:");
		timePane.add(ToLabel, "2, 4");

		JSlider toTime = new JSlider();
		timePane.add(toTime, "4, 4");

		toTextField = new JTextField();
		toTextField.setColumns(10);
		timePane.add(toTextField, "6, 4, fill, default");

		JPanel textFilterPane = new JPanel();
		textFilterPane.setBorder(new TitledBorder(null, "Text", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		add(textFilterPane, "3, 5, 1, 2, fill, top");
		textFilterPane.setLayout(new FormLayout(new ColumnSpec[]
		{ FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"), }, new RowSpec[]
		{ FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, }));

		JLabel classLabel = new JLabel("Klasse:");
		textFilterPane.add(classLabel, "2, 1, left, default");

		classTextField = new JTextField();
		textFilterPane.add(classTextField, "4, 1, fill, default");
		classTextField.setColumns(10);

		JLabel threadLabel = new JLabel("Thread:");
		textFilterPane.add(threadLabel, "2, 3, left, default");

		threadTextField = new JTextField();
		textFilterPane.add(threadTextField, "4, 3, fill, default");
		threadTextField.setColumns(10);

		fileChooserPanel = new JPanel();
		fileChooserPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "File",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		add(fileChooserPanel, "3, 11, fill, fill");
		fileChooserPanel.setLayout(new FormLayout(new ColumnSpec[]
		{ FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("default:grow"), FormSpecs.RELATED_GAP_COLSPEC, },
				new RowSpec[]
				{ FormSpecs.RELATED_GAP_ROWSPEC, RowSpec.decode("max(65dlu;default):grow"),
						FormSpecs.RELATED_GAP_ROWSPEC, RowSpec.decode("bottom:default:grow"),
						FormSpecs.RELATED_GAP_ROWSPEC, }));

		JPanel panel = new JPanel();
		fileChooserPanel.add(panel, "2, 2, fill, fill");

		filedTextField = new JTextField();
		panel.add(filedTextField);
		filedTextField.setColumns(10);

		JPanel panel_1 = new JPanel();
		fileChooserPanel.add(panel_1, "2, 4, fill, fill");

		JButton openButton = new JButton("Open");
		panel_1.add(openButton);

		loadButton = new JButton("Load");

		panel_1.add(loadButton);
	}

	public JPanel getFileChooserPanel()
	{
		return fileChooserPanel;
	}

	public JTextField getFiledTextField()
	{
		return filedTextField;
	}

	public JButton getLoadButton()
	{
		return loadButton;
	}
}
