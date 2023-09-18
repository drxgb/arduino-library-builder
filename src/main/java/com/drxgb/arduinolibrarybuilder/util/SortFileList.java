package com.drxgb.arduinolibrarybuilder.util;

import java.io.File;
import java.util.Comparator;

/**
 * Classifica lista de arquivos em forma de texto
 * @author Dr.XGB
 * @version 1.0.0
 */
public class SortFileList implements Comparator<String>
{
	/**
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	@Override
	public int compare(String a, String b)
	{
		return new SortDirectory().compare(new File(a), new File(b));
	}
}
