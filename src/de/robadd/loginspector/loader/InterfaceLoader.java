package de.robadd.loginspector.loader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.google.common.collect.ImmutableSet;
import com.google.common.reflect.ClassPath;
import com.google.common.reflect.ClassPath.ClassInfo;

import de.robadd.loginspector.reader.model.events.Event;

public class InterfaceLoader
{

	public static List<Class<? super Event>> getEventClasses()
	{
		return getClassesImplementingInterface(Event.class);
	}

	public static <T> List<Class<? super T>> getSubTypes(final Class<T> clazz)
	{
		if (clazz.isInterface())
		{
			return getClassesImplementingInterface(clazz);
		}
		else
		{
			return getClassesExtendingClass(clazz);
		}
	}

	@SuppressWarnings("unchecked")
	public static <T> List<Class<? super T>> getClassesImplementingInterface(final Class<T> clazz)
	{
		List<Class<? super T>> retVal = new ArrayList<>();
		final ClassLoader loader = ClassLoader.getSystemClassLoader();
		try
		{
			for (ClassInfo classInfo : ClassPath.from(loader).getAllClasses())
			{
				if (classInfo.getName().startsWith("de.robadd") && Arrays
						.asList(classInfo.load().getInterfaces())
						.stream()
						.anyMatch(a -> a.getName().equals(clazz.getName())))
				{
					retVal.add((Class<? super T>) classInfo.load());
				}
			}

		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return retVal;
	}

	@SuppressWarnings("unchecked")
	public static <T> List<Class<? super T>> getClassesExtendingClass(final Class<T> clazz)
	{
		List<Class<? super T>> retVal = new ArrayList<>();
		final ClassLoader loader = ClassLoader.getSystemClassLoader();
		try
		{
			final ImmutableSet<ClassInfo> allClasses = ClassPath.from(loader).getAllClasses();
			final List<ClassInfo> packageFilteredClasses = allClasses
					.stream()
					.filter(a -> a.getName().startsWith("de.robadd"))
					.collect(Collectors.toList());
			for (ClassInfo classInfo : packageFilteredClasses)
			{

				if (classInfo.getName().startsWith("de.robadd") && Arrays
						.asList(classInfo.load().getSuperclass())
						.stream()
						.anyMatch(a -> a != null && a.getName().equals(clazz.getName())))
				{
					retVal.add((Class<? super T>) classInfo.load());
				}
			}

		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return retVal;
	}
}
