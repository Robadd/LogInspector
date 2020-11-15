package de.robadd.loginspector.ui;

import java.util.ArrayList;
import java.util.List;

import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

public class RowSpecs
{
	/**
	 * Returns row definitions for filtering panel. Always includes one top (for
	 * time) and one bottom (Open/Save) fixed row definition
	 *
	 * @param filterAmount determines how many additional filters should be returned
	 * @return the rowspecs
	 */
	public static RowSpec[] getRowspecs(final Integer filterAmount)
	{
		final List<RowSpec> retVal = new ArrayList<>();
		retVal.add(FormSpecs.UNRELATED_GAP_ROWSPEC);
		retVal.add(RowSpec.decode("top:default"));
		retVal.add(FormSpecs.RELATED_GAP_ROWSPEC);
		for (int i = 0; i < filterAmount; i++)
		{
			retVal.add(RowSpec.decode("fill:default:grow"));
			retVal.add(FormSpecs.RELATED_GAP_ROWSPEC);
		}
		retVal.add(RowSpec.decode("bottom:default"));
		retVal.add(FormSpecs.UNRELATED_GAP_ROWSPEC);

		return retVal.toArray(new RowSpec[0]);
	}

	public static Integer getLastRowIndex(final Integer filterAmount)
	{
		return 4 + filterAmount * 2;
	}

	public static Integer getNthFilterRowIndex(final Integer nth)
	{
		return 2 + nth * 2;
	}
}
