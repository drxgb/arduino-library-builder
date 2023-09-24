package com.drxgb.arduinolibrarybuilder.exception;

/**
 * Trata os erros quando não há um estratégia de leitura ou gravação
 * de dados em um arquivo.
 * 
 * @author Dr.XGB
 * @version 1.0.0
 */
public class StrategyNotFoundException extends RuntimeException
{
	/*
	 * ===========================================================
	 * 			*** CONSTANTES ***
	 * ===========================================================
	 */
	
	private static final long serialVersionUID = 1L;
	
	
	/*
	 * ===========================================================
	 * 			*** CONSTRUTORES ***
	 * ===========================================================
	 */
	
	public StrategyNotFoundException()
	{
		this("No strategy found to handle this file");
	}

	public StrategyNotFoundException(String message)
	{
		super(message);
	}
}
