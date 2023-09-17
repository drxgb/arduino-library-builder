package com.drxgb.arduinolibrarybuilder.app;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Representa a aplicação principal no qual é mostrada a interface gráfica.
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
	 * Nome da aplicação
	 */
	public static final String APP_NAME = "Arduino Library Builder";
	
	/**
	 * Versão da aplicação
	 */
	public static final String APP_VERSION = "1.0.0";
	
	
	/*
	 * ===========================================================
	 * 			*** MÉTODOS PÚBLICOS ESTÁTICOS ***
	 * ===========================================================
	 */
	
	/**
	 * Inicializa a aplicação
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
	 * 			*** MÉTODOS IMPLEMENTADOS ***
	 * ===========================================================
	 */
	
	/**
	 * @see javafx.application.Application#start(javafx.stage.Stage)
	 */
	@Override
	public void start(Stage primaryStage) throws Exception
	{
		// TODO Criar janela
		System.out.println("Começou");
	}
}
