package com.drxgb.arduinolibrarybuilder;

import com.drxgb.arduinolibrarybuilder.controller.Controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
	
	/**
	 * Cena da janela principal
	 */
	private static Scene mainScene;
	
	
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
	
	
	/**
	 * Recebe a cena principal da aplica��o
	 * @return A cena da aplica��o principal
	 */
	public static Scene getScene()
	{
		return mainScene;
	}
	
	
	/**
	 * Recebe a janela principal da aplica��o
	 * @return
	 */
	public static Stage getStage()
	{
		return mainScene != null ? (Stage) mainScene.getWindow() : null;
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
	public void start(Stage stage) throws Exception
	{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("view/MainView.fxml"));
		Parent root = loader.load();
		Controller controller = loader.getController();
		
		mainScene = new Scene(root);
		// TODO: Carregar CSS
		
		// TODO: Inserir �cone da aplica��o
		stage.setOnCloseRequest(ev -> controller.onCloseAction());
		stage.setResizable(false);
		stage.setScene(mainScene);
		stage.setTitle(APP_NAME);
		stage.show();
	}
}
