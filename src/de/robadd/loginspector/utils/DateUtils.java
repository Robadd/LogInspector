package de.robadd.loginspector.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.annotation.Nullable;

public class DateUtils
{
	@Nullable
	public static String fromDate(final Calendar date)
	{
		SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy hh:mm:ss");
		return df.format(date);
	}

	@Nullable
	public static Calendar fromString(final String dateString)
	{
		try
		{
			SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy hh:mm:ss");
			Date date = df.parse(dateString);
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			return cal;
		}
		catch (ParseException e)
		{
			return null;
		}
	}
}
