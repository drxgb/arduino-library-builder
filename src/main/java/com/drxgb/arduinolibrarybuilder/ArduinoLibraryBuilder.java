package com.drxgb.arduinolibrarybuilder;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
	
	/**
	 * Cena da janela principal
	 */
	private static Scene mainScene;
	
	
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
	
	
	/**
	 * Recebe a cena principal da aplicação
	 * @return A cena da aplicação principal
	 */
	public static Scene getScene()
	{
		return mainScene;
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
	public void start(Stage stage) throws Exception
	{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("view/MainView.fxml"));
		Parent root = loader.load();
		
		mainScene = new Scene(root);
		// TODO: Carregar CSS
		
		// TODO: Inserir ícone da aplicação
		stage.setResizable(false);
		stage.setScene(mainScene);
		stage.setTitle(APP_NAME);
		stage.show();
	}
}
