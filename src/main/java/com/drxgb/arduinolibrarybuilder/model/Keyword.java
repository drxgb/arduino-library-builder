package com.drxgb.arduinolibrarybuilder.model;

import com.drxgb.arduinolibrarybuilder.util.KeywordTokenType;
import com.drxgb.arduinolibrarybuilder.util.RSyntaxTextAreaTokenType;

/**
 * Representa o campo da palavra-chave em keywords.txt
 * 
 * @author Dr.XGB
 * @version 1.0.0
 */
public class Keyword
{
	/*
	 * ===========================================================
	 * 			*** ATRIBUTOS ***
	 * ===========================================================
	 */
	
	private String name;
	private String referenceLink;
	private KeywordTokenType tokenType;
	private RSyntaxTextAreaTokenType rSyntaxTextAreaTokenType;
	
	
	/*
	 * ===========================================================
	 * 			*** CONSTRUTORES ***
	 * ===========================================================
	 */
	
	public Keyword() {}
	
	
	/*
	 * ===========================================================
	 * 			*** GETTERS E SETTERS ***
	 * ===========================================================
	 */

	public String getName()
	{
		return name;
	}


	public String getReferenceLink()
	{
		return referenceLink;
	}


	public KeywordTokenType getTokenType()
	{
		return tokenType;
	}


	public RSyntaxTextAreaTokenType getRSyntaxTextAreaTokenType()
	{
		return rSyntaxTextAreaTokenType;
	}


	public void setName(String name)
	{
		this.name = name;
	}


	public void setReferenceLink(String referenceLink)
	{
		this.referenceLink = referenceLink;
	}


	public void setTokenType(KeywordTokenType tokenType)
	{
		this.tokenType = tokenType;
	}


	public void setRSyntaxTextAreaTokenType(RSyntaxTextAreaTokenType rSyntaxTextAreaTokenType)
	{
		this.rSyntaxTextAreaTokenType = rSyntaxTextAreaTokenType;
	}	
	
	
	/*
	 * ===========================================================
	 * 			*** TO STRING ***
	 * ===========================================================
	 */

	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		builder.append("Keyword [\n\tname: ").append(name).append(",\n\treferenceLink: ").append(referenceLink)
				.append(",\n\ttokenType: ").append(tokenType).append(",\n\trSyntaxTextAreaTokenType: ")
				.append(rSyntaxTextAreaTokenType).append("\n]");
		return builder.toString();
	}
}
