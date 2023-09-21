package com.drxgb.arduinolibrarybuilder.builder;

import java.util.List;

import com.drxgb.arduinolibrarybuilder.util.KeywordTokenType;
import com.drxgb.arduinolibrarybuilder.util.RSyntaxTextAreaTokenType;

/**
 * Gerador de listas de tipos de token
 * 
 * @author Dr;XGB
 * @version 1.0.0
 */
public class TokenTypeBuilder
{
	/*
	 * ===========================================================
	 * 			*** MÉTODOS PÚBLICOS ***
	 * ===========================================================
	 */
	
	/**
	 * Insere os campos de KEYWORD_TOKENTYPE a uma lista
	 * @param list A lista onde serão inseridos os campos
	 */
	public void appendKeywordTokenTypes(List<KeywordTokenType> list)
	{
		list.add(KeywordTokenType.KEYWORD1);
		list.add(KeywordTokenType.KEYWORD2);
		list.add(KeywordTokenType.KEYWORD3);
		list.add(KeywordTokenType.LITERAL1);
		list.add(KeywordTokenType.LITERAL2);
	}
	
	
	/**
	 * Insere os campos de RSYNTAXTEXTAREA_TOKENTYPE a uma lista
	 * @param list A lista onde serão inseridos os campos
	 */
	public void appendRSyntaxTextAreaTokenType(List<RSyntaxTextAreaTokenType> list)
	{
		list.add(null);
		list.add(RSyntaxTextAreaTokenType.RESERVED_WORD);
		list.add(RSyntaxTextAreaTokenType.RESERVED_WORD_2);
		list.add(RSyntaxTextAreaTokenType.DATA_TYPE);
		list.add(RSyntaxTextAreaTokenType.PREPROCESSOR);
		list.add(RSyntaxTextAreaTokenType.LITERAL_BOOLEAN);
	}
}
