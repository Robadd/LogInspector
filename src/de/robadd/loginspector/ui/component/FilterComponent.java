package de.robadd.loginspector.ui.component;

import java.util.List;

import javax.swing.AbstractListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

import de.robadd.loginspector.EventIndex;
import de.robadd.loginspector.exception.NotImplementedException;
import de.robadd.loginspector.reader.filter.MessageFilter;
import de.robadd.loginspector.reader.filter.MessageFilterFactory;
import de.robadd.loginspector.reader.filter.MessageFilterFactory.Type;

/**
 * @author Robert Kraus
 */
public abstract class FilterComponent<T> extends JPanel
{
	private static final long serialVersionUID = -5686261428459316681L;
	private JScrollPane panel;
	private JList<T> list;
	private List<T> values;
	private List<T> selectedValues;
	private String label;
	private Type type;

	FilterComponent(final String argLabel, final Type type)
	{
		super();
		this.label = argLabel;
		this.type = type;
		init();
	}

	private void init()
	{
		setBorder(new TitledBorder(null, label, TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setLayout(new FormLayout(new ColumnSpec[]
		{ FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("default:grow"), FormSpecs.RELATED_GAP_COLSPEC, },
				new RowSpec[]
				{ RowSpec.decode("fill:max(51dlu;default):grow"), }));
		list = new JList<>();
		panel = new JScrollPane(list);
		add(panel, "2, 1, fill, fill");
		list.setVisibleRowCount(5);
	}

	@SuppressWarnings("unchecked")
	public void setValues()
	{
		switch (type)
		{
			case CLASS:
				setValues((List<T>) EventIndex.getInstance().getClassNames());
				break;
			case THREAD:
				setValues((List<T>) EventIndex.getInstance().getThreadNames());
				break;
			case LOGLEVEL:
				setValues((List<T>) EventIndex.getInstance().getLogLevels());
				break;
			case TIME:
			case API:
			default:
				throw new NotImplementedException();
		}
	}

	/**
	 * @param values the values to set
	 */
	public void setValues(final List<T> argValues)
	{
		this.values = argValues;
		list.setModel(new AbstractListModel<T>()
		{
			private static final long serialVersionUID = -1834746057622280163L;

			@Override
			public T getElementAt(final int index)
			{
				return values.get(index);
			}

			@Override
			public int getSize()
			{
				return values.size();
			}
		});
		list.setEnabled(true);
	}

	/**
	 * @return the messageFilter
	 */
	@SuppressWarnings("unchecked")
	public MessageFilter getMessageFilter()
	{
		switch (type)
		{
			case CLASS:
				return MessageFilterFactory.classFilter((List<String>) values);
			case THREAD:
				return MessageFilterFactory.threadFilter((List<String>) values);
			case LOGLEVEL:
				return MessageFilterFactory.logLevelFilter((List<String>) values);
			case TIME:
			case API:
			default:
				throw new NotImplementedException();
		}
	}

	/**
	 * @return the selectedValues
	 */
	public List<T> getSelectedValues()
	{
		return list.getSelectedValuesList();
	}
}
