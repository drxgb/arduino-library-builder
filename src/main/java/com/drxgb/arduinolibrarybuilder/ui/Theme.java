package com.drxgb.arduinolibrarybuilder.ui;

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
	private String externalForm;
	

	/*
	 * ===========================================================
	 * 			*** CONSTRUTORES ***
	 * ===========================================================
	 */
	
	public Theme(String name, String externalForm)
	{
		this.name = name;
		this.externalForm = externalForm;
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


	public String getExternalForm()
	{
		return externalForm;
	}


	public void setName(String name)
	{
		this.name = name;
	}


	public void setExternalForm(String externalForm)
	{
		this.externalForm = externalForm;
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
		result = prime * result + ((externalForm == null) ? 0 : externalForm.hashCode());
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
		if (externalForm == null)
		{
			if (other.externalForm != null)
				return false;
		}
		else if (!externalForm.equals(other.externalForm))
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
		StringBuilder builder = new StringBuilder();
		builder.append(name)
			.append(" <")
			.append(externalForm)
			.append(">");
		return builder.toString();
	}
	
	
	
}
