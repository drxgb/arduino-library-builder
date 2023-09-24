package com.drxgb.arduinolibrarybuilder.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Classe com funções utilitárias para as propriedades
 * de biblioteca
 */
public abstract class LibraryPropertiesUtils
{
	/*
	 * ===========================================================
	 * 			*** MÉTODOS PÚBLICOS ESTÁTICOS ***
	 * ===========================================================
	 */
	
	/**
	 * Constrói o padrão de nome e email do arquivo <code>library.properties</code>,
	 * formando o seguinte padrão:
	 * <p><cdoe>name &lt;email&gt;</code></p>
	 * @param name O nome
	 * @param email O endereço de email
	 * @return Resultado dos dados
	 */
	public static String makeNameAndEmailProperty(String name, String email)
	{
		if (name == null)
			return null;
		
		StringBuilder sb = new StringBuilder();
		
		sb.append(name);
		if (email != null)
			sb.append(' ').append('<').append(email).append('>');
		return sb.toString();
	}
	
	
	/**
	 * Monta um string através de uma lista. O texto deverá
	 * ser separado por vírgulas
	 * @param values A lista de valores
	 * @return O texto da lista separado por vírgulas
	 */
	public static String makePropertyFromList(List<String> values)
	{
		if (values == null || values.isEmpty())
			return null;
		
		StringBuilder sb = new StringBuilder();
		
		for (String value : values)
			sb.append(value).append(',');
		
		return sb.substring(0, sb.length() - 1);
	}
	
	
	/**
	 * Divide os valores de nome e email e entrega um conjunto
	 * destes valores
	 * @param value O texto a ser dividido
	 * @return O conjunto de valores contendo nome e email
	 */
	public static Map<String, String> extractNameAndEmailProperty(String value)
	{		
		Map<String, String> result = new HashMap<>();
		String name = null;
		String email = null;
		
		if (value != null)
		{
			final int emailIndex = value.lastIndexOf('<');
			
			name = value.substring(0, emailIndex).trim();
			email = value.substring(emailIndex + 1, value.length() - 1);
		}

		result.put("name", name);
		result.put("email", email);
		
		return result;
	}
	
	
	/**
	 * Extrai o valor em texto e converte para lista
	 * @param value O valor a ser dividido
	 * @return A lista contendo todos os valores separados
	 */
	public static List<String> extractPropertyToList(String value)
	{
		if (value == null)
			return new ArrayList<>();		
		return Arrays.asList(value.split(","));
	}
}
