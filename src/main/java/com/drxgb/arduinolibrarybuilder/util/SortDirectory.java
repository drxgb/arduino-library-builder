package com.drxgb.arduinolibrarybuilder.util;

import java.io.File;
import java.util.Comparator;

/**
 * Compara arquivos separados por diretório e em ordem alfabética
 * @author Dr.XGB
 * @version 1.0.0
 */
public class SortDirectory implements Comparator<File>
{
	/**
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	@Override
	public int compare(File a, File b)
	{
		if (a == null)
		{
			if (b == null)
				return 0;
			return 1;
		}
		else if (b ==  null)
			return -1;
		
		if (a.isDirectory())
		{
			if (!b.isDirectory())
				return -1;
		}
		else if (b.isDirectory())
			return 1;
		
		return a.getName().compareTo(b.getName());
	}
}
