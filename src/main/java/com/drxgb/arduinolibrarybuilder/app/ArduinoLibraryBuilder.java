package com.drxgb.arduinolibrarybuilder.app;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Representa a aplica��o principal no qual � mostrada a interface gr�fica.
 * @author Dr.XGB
 * @version 1.0.0
 */
public final class ArduinoLibraryBuilder extends Application
{
	/*
	 * ===========================================================
	 * 			*** CONSTANTES ***
	 * ===========================================================
	 */
	
	/**
	 * Nome da aplica��o
	 */
	public static final String APP_NAME = "Arduino Library Builder";
	
	/**
	 * Vers�o da aplica��o
	 */
	public static final String APP_VERSION = "1.0.0";
	
	
	/*
	 * ===========================================================
	 * 			*** M�TODOS P�BLICOS EST�TICOS ***
	 * ===========================================================
	 */
	
	/**
	 * Inicializa a aplica��o
	 * @param args Argumentos passados do sistema operacional para o programa
	 */
	public static void main(String[] args)
	{
		try
		{
			launch(args);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	/*
	 * ===========================================================
	 * 			*** M�TODOS IMPLEMENTADOS ***
	 * ===========================================================
	 */
	
	/**
	 * @see javafx.application.Application#start(javafx.stage.Stage)
	 */
	@Override
	public void start(Stage primaryStage) throws Exception
	{
		// TODO Criar janela
		System.out.println("Come�ou");
	}
}
