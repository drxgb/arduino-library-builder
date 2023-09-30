package com.drxgb.arduinolibrarybuilder.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Scanner;

import com.drxgb.arduinolibrarybuilder.model.Keyword;
import com.drxgb.arduinolibrarybuilder.model.KeywordTokenType;
import com.drxgb.arduinolibrarybuilder.model.RSyntaxTextAreaTokenType;

/**
 * Define ações de gravação e leitura do arquivo de palavras-chave
 * 
 * @author Dr.XGB
 * @version 1.0.0
 */
public class KeywordsFileStrategy implements FileStrategy
{
	/*
	 * ===========================================================
	 * 			*** CONSTANTES ***
	 * ===========================================================
	 */
	
	private static final String KEYWORD_SEPARATOR = "\t";
	
	
	/*
	 * ===========================================================
	 * 			*** ATRIBUTOS ***
	 * ===========================================================
	 */
	
	private List<Keyword> keywords;
	
	
	/*
	 * ===========================================================
	 * 			*** CONSTRUTORES ***
	 * ===========================================================
	 */
	
	public KeywordsFileStrategy(List<Keyword> keywords)
	{
		this.keywords = keywords;
	}
	

	/*
	 * ===========================================================
	 * 			*** MÉTODOS IMPLEMENTADOS ***
	 * ===========================================================
	 */

	/**
	 * @throws IOException 
	 * @see com.drxgb.arduinolibrarybuilder.io.FileStrategy#load(java.io.InputStream)
	 */
	@Override
	public void load(InputStream input) throws IOException
	{
		try (Scanner scanner = new Scanner(input))
		{			
			while (scanner.hasNextLine())
			{
				String[] values = scanner.nextLine().split("\\t");
				final int LENGTH = values.length;
				
				if (LENGTH > 0)
				{
					Keyword keyword = new Keyword();
					
					keyword.setName(values[0]);					
					if (LENGTH > 1)
						keyword.setTokenType(KeywordTokenType.valueOf(values[1]));
					if (LENGTH > 2)
						keyword.setReferenceLink(values[2]);
					if (LENGTH > 3)
						keyword.setRSyntaxTextAreaTokenType(RSyntaxTextAreaTokenType.valueOf(values[3]));

					keywords.add(keyword);
				}
			}
		}
	}


	/**
	 * @throws IOException 
	 * @see com.drxgb.arduinolibrarybuilder.io.FileStrategy#save(java.io.OutputStream)
	 */
	@Override
	public void save(OutputStream output) throws IOException
	{
		final byte[] SEP = KEYWORD_SEPARATOR.getBytes();
		
		for (Keyword keyword : keywords)
		{
			KeywordTokenType keywordTokenType = keyword.getTokenType();
			RSyntaxTextAreaTokenType rSyntaxTextAreaTokenType = keyword.getRSyntaxTextAreaTokenType();
			
			writeValue(output, keyword.getName());
			output.write(SEP);
			
			if (keywordTokenType != null)
				writeValue(output, keywordTokenType.toString());
			output.write(SEP);
			writeValue(output, keyword.getReferenceLink());
			output.write(SEP);
			
			if (rSyntaxTextAreaTokenType != null)
				writeValue(output, rSyntaxTextAreaTokenType.toString());
			writeValue(output, "\n");
		}
	}
	
	
	/*
	 * ===========================================================
	 * 			*** MÉTODOS PRIVADOS ***
	 * ===========================================================
	 */
	
	/**
	 * @see com.drxgb.arduinolibrarybuilder.io.FileStrategy#getResult()
	 */
	@Override
	public Object getResult()
	{		
		return keywords;
	}


	/**
	 * Escreve o valor caso o mesmo não for nulo
	 * @param output O arquivo a ser escrito
	 * @param value Valor a ser escrito
	 * @throws IOException Quando ocorre um erro ao tentar gravar o valor no arquivo
	 */
	private void writeValue(OutputStream output, String value) throws IOException
	{
		if (value != null)
			output.write(value.getBytes());
	}
}
