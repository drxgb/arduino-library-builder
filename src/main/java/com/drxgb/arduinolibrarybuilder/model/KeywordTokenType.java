package com.drxgb.arduinolibrarybuilder.model;

/**
 * Representa o tipo de token da palavra-chave
 * 
 * @autor Dr.XGB
 * @version 1.0.0
 */
public enum KeywordTokenType
{
	KEYWORD1("datatypes"),
	KEYWORD2("functions"),
	KEYWORD3("structures"),
	LITERAL1("constants"),
	LITERAL2("?");
	
	
	/*
	 * ===========================================================
	 * 			*** ATRIBUTOS ***
	 * ===========================================================
	 */	
	
	private final String hint;
	
	
	/*
	 * ===========================================================
	 * 			*** CONSTRUTORES ***
	 * ===========================================================
	 */
	
	KeywordTokenType(String hint)
	{
		this.hint = hint;
	}
	
	
	/*
	 * ===========================================================
	 * 			*** MÉTODOS PÚBLICOS ***
	 * ===========================================================
	 */
	
	public String print()
	{
		StringBuilder sb = new StringBuilder();
		
		sb.append(name())
			.append(" (")
			.append(hint)
			.append(")");
		return sb.toString();
	}

	
	/*
	 * ===========================================================
	 * 			*** GETTERS ***
	 * ===========================================================
	 */
	
	public String getHint()
	{
		return hint;
	}	
}
