package com.drxgb.arduinolibrarybuilder.ui;

import com.drxgb.arduinolibrarybuilder.builder.TokenTypeBuilder;
import com.drxgb.arduinolibrarybuilder.model.KeywordTokenType;
import com.drxgb.arduinolibrarybuilder.model.RSyntaxTextAreaTokenType;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Armazena a lista dos campos de tipos de token
 */
public class TokenTypeFields
{
	/*
	 * ===========================================================
	 * 			*** ATRIBUTOS ***
	 * ===========================================================
	 */
	
	private static TokenTypeFields instance;
	private ObservableList<KeywordTokenType> _keywordTokenTypes;
	private ObservableList<RSyntaxTextAreaTokenType> _rSyntaxTextAreaTokenTypes;
	
	
	/*
	 * ===========================================================
	 * 			*** CONSTRUTORES ***
	 * ===========================================================
	 */
	
	private TokenTypeFields()
	{
		_keywordTokenTypes = FXCollections.observableArrayList();
		_rSyntaxTextAreaTokenTypes = FXCollections.observableArrayList();
	}
	
	
	/*
	 * ===========================================================
	 * 			*** MÉTODOS PÚBLICOS ESTÁTICOS ***
	 * ===========================================================
	 */
	
	/**
	 * Entrega a lista dos campos para KEYWORD_TOKENTYPES
	 * @return
	 */
	public static ObservableList<KeywordTokenType> keywordTokenTypes()
	{
		ObservableList<KeywordTokenType> list = getInstance()._keywordTokenTypes;
		
		if (list.isEmpty())
		{
			TokenTypeBuilder builder = new TokenTypeBuilder();
			builder.appendKeywordTokenTypes(list);
		}
		
		return list;
	}
	
	
	/**
	 * Entrega a lista dos campos para RSYNTAXTEXTAREA_TOKENTYPES
	 * @return
	 */
	public static ObservableList<RSyntaxTextAreaTokenType> rSyntaxTextAreaTokenTypes()
	{
		ObservableList<RSyntaxTextAreaTokenType> list = getInstance()._rSyntaxTextAreaTokenTypes;
		
		if (list.isEmpty())
		{
			TokenTypeBuilder builder = new TokenTypeBuilder();
			builder.appendRSyntaxTextAreaTokenType(list);
		}
		
		return list;
	}
	
	 
	/*
	 * ===========================================================
	 * 			*** MÉTODOS PRIVADOS ESTÁTICOS ***
	 * ===========================================================
	 */
	
	/**
	 * Recebe ou cria uma instância singleton
	 * @return
	 */
	private static TokenTypeFields getInstance()
	{
		if (instance == null)
			instance = new TokenTypeFields();
		return instance;
	}
}
