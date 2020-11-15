package de.robadd.loginspector.ui.component;

import java.awt.Color;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

import de.robadd.loginspector.EventIndex;
import de.robadd.loginspector.reader.filter.MessageFilter;
import de.robadd.loginspector.reader.filter.MessageFilterFactory;
import de.robadd.loginspector.ui.RowSpecs;

public class ServerlogSettingsPanel extends JPanel
{
	private JTextField fromTextField;
	private static final long serialVersionUID = 5634933344406960190L;
	private JTextField toTextField;
	private JList<String> threadList;
	private JPanel fileChooserPanel;
	private JButton loadButton;
	private JList<String> list;
	private JPanel timePane;
	private FilterComponent<String> classPanel;
	private JButton openButton;
	private List<FilterComponent<?>> filterComponents;
	private Integer filterCount;

	public ServerlogSettingsPanel(final Integer argFilterCount)
	{
		this.filterCount = argFilterCount;
		setLayout(new FormLayout(getColSpec(), RowSpecs.getRowspecs(filterCount)));
		classPanel = FilterComponentFactory.classNameComponent();
		add(classPanel, "2, " + RowSpecs.getNthFilterRowIndex(filterCount) + ", fill, fill");
		addTimePanel();
		// addTextFilterPanel();
		addFileChooserPanel();
	}

	private void addTextFilterPanel()
	{
		JPanel textFilterPane = new JPanel();
		textFilterPane.setBorder(new TitledBorder(null, "Text", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		add(textFilterPane, "2, 5, 1, 2, fill, top");
		textFilterPane.setLayout(new FormLayout(new ColumnSpec[]
		{ FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"), }, new RowSpec[]
		{ RowSpec.decode("max(51dlu;default)"), FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, }));

		JLabel classLabel = new JLabel("Klasse:");
		textFilterPane.add(classLabel, "2, 1, left, default");

		JScrollPane scrollPane = new JScrollPane();
		textFilterPane.add(scrollPane, "4, 1, fill, fill");

		JLabel threadLabel = new JLabel("Thread:");
		textFilterPane.add(threadLabel, "2, 3, left, default");

		JScrollPane scrollPane_1 = new JScrollPane();
		textFilterPane.add(scrollPane_1, "4, 3, fill, fill");

		threadList = new JList<>();
		threadList.setVisibleRowCount(5);
		scrollPane_1.setViewportView(threadList);
	}

	private void addTimePanel()
	{
		timePane = new JPanel();
		add(timePane, "2, 2, fill, fill");

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
	}

	private void addFileChooserPanel()
	{
		fileChooserPanel = new JPanel();
		fileChooserPanel
				.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "File", TitledBorder.LEADING,
						TitledBorder.TOP, null, new Color(0, 0, 0)));
		add(fileChooserPanel, "2, " + RowSpecs.getLastRowIndex(filterCount) + ", fill, fill");
		fileChooserPanel.setLayout(new FormLayout(new ColumnSpec[]
		{ FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("left:default:grow"), FormSpecs.RELATED_GAP_COLSPEC, },
				new RowSpec[]
				{ RowSpec.decode("bottom:default:grow"), FormSpecs.RELATED_GAP_ROWSPEC, }));

		JPanel panel_1 = new JPanel();
		fileChooserPanel.add(panel_1, "2, 1, left, fill");

		openButton = new JButton("Open");
		panel_1.add(openButton);

		loadButton = new JButton("Load");

		panel_1.add(loadButton);
	}

	public JPanel getFileChooserPanel()
	{
		return fileChooserPanel;
	}

	public JButton getLoadButton()
	{
		return loadButton;
	}

	public JList getLogLevelList()
	{
		return list;
	}

	public JPanel getTimePane()
	{
		return timePane;
	}

	public void fillFilterBoxes()
	{
		classPanel.setValues(EventIndex.getInstance().getClassNames());
	}

	public JButton getOpenButton()
	{
		return openButton;
	}

	public List<String> getSelectedThreads()
	{
		return threadList.getSelectedValuesList();
	}

	public MessageFilter getMessageFilter()
	{
		return MessageFilterFactory
				.of(filterComponents.stream().map(FilterComponent::getMessageFilter).collect(Collectors.toList()));
	}

	private ColumnSpec[] getColSpec()
	{
		return new ColumnSpec[]
		{ FormSpecs.UNRELATED_GAP_COLSPEC, ColumnSpec.decode("left:default:grow"), FormSpecs.UNRELATED_GAP_COLSPEC, };
	}
}
