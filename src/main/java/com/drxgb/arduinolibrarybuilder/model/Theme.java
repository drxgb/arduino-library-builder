package com.drxgb.arduinolibrarybuilder.model;

/**
 * Representa o tema de estilo da interface gráfica
 * 
 * @author Dr.XGB
 * @version 1.0.0
 */
public class Theme
{
	/*
	 * ===========================================================
	 * 			*** ATRIBUTOS ***
	 * ===========================================================
	 */
	
	private String name;
	private String path;
	

	/*
	 * ===========================================================
	 * 			*** CONSTRUTORES ***
	 * ===========================================================
	 */
	
	public Theme(String name, String path)
	{
		this.name = name;
		this.path = path;
	}


	/*
	 * ===========================================================
	 * 			*** GETTERS E SETTERS ***
	 * ===========================================================
	 */
	
	public String getName()
	{
		return name;
	}


	public String getPath()
	{
		return path;
	}


	public void setName(String name)
	{
		this.name = name;
	}


	public void setPath(String path)
	{
		this.path = path;
	}


	/*
	 * ===========================================================
	 * 			*** HASHCODE E EQUALS ***
	 * ===========================================================
	 */
	
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((path == null) ? 0 : path.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
	
	
	/**
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Theme other = (Theme) obj;
		if (path == null)
		{
			if (other.path != null)
				return false;
		}
		else if (!path.equals(other.path))
			return false;
		if (name == null)
		{
			if (other.name != null)
				return false;
		}
		else if (!name.equals(other.name))
			return false;
		return true;
	}

	
	/*
	 * ===========================================================
	 * 			*** TO STRING ***
	 * ===========================================================
	 */

	/**
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return name;
	}
}
