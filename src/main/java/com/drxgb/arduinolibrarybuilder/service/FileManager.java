package com.drxgb.arduinolibrarybuilder.service;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import com.drxgb.arduinolibrarybuilder.exception.StrategyNotFoundException;
import com.drxgb.arduinolibrarybuilder.io.FileStrategy;

/**
 * Responsável por gravar e carregar dados em um arquivo
 * 
 * @author Dr.XGB
 * @version 1.0.0
 */
public class FileManager
{
	/*
	 * ===========================================================
	 * 			*** ATRIBUTOS ***
	 * ===========================================================
	 */
	
	private FileStrategy strategy;
	
	
	/*
	 * ===========================================================
	 * 			*** MÉTODOS PÚBLICOS ***
	 * ===========================================================
	 */
	
	/**
	 * Carrega um arquivo e aplica a um conjunto de dados do sistema
	 * @param file Arquivo a ser carregado
	 * @throws IOException Quando ocorre um problema na leitura do arquivo
	 * @throws StrategyNotFoundException Quando a estratégia não é definida antes de executar a ação
	 */
	public void load(File file) throws IOException
	{
		if (file != null)
		{
			try (BufferedInputStream input = new BufferedInputStream(new FileInputStream(file)))
			{
				if (strategy == null)
					throw new StrategyNotFoundException();			
				strategy.load(input);
			}
		}
	}
	
	
	/**
	 * Grava os dados em um arquivo
	 * @param file Arquivo a ser gravado
	 * @throws IOException Quando ocorre um problema na leitura do arquivo
	 * @throws StrategyNotFoundException Quando a estratégia não é definida antes de executar a ação
	 */
	public void save(File file) throws IOException
	{
		if (file != null)
		{
			try (BufferedOutputStream output = new BufferedOutputStream(new FileOutputStream(file)))
			{
				if (strategy == null)
					throw new StrategyNotFoundException();
				strategy.save(output);
			}
		}
	}
	
	
	/**
	 * Recebe o resultado do gerenciamento do arquivo
	 * @return O resultado
	 */
	public Object result()
	{
		return strategy.getResult();
	}
	
	
	/*
	 * ===========================================================
	 * 			*** GETTERS E SETTERS ***
	 * ===========================================================
	 */

	public FileStrategy getStrategy()
	{
		return strategy;
	}

	public void setStrategy(FileStrategy strategy)
	{
		this.strategy = strategy;
	}	
}
