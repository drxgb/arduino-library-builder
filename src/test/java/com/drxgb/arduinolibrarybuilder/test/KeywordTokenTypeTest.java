package com.drxgb.arduinolibrarybuilder.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.drxgb.arduinolibrarybuilder.util.KeywordTokenType;

/**
 * Realiza testes com o enumerador KeywordTokenType
 * 
 * @author Dr.XGB
 * @version 1.0.0
 * @see KeywordTokenType
 */
class KeywordTokenTypeTest
{
	@Test
	void test()
	{
		KeywordTokenType type;
		String field;
		
		type = KeywordTokenType.KEYWORD1;
		field = type.print();
		System.out.println(field);
		
		assertEquals(type, KeywordTokenType.KEYWORD1);
		assertEquals(field, "KEYWORD1 (datatypes)");
	}
}
