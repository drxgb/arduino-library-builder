package com.drxgb.arduinolibrarybuilder;

import java.io.InputStream;

import com.drxgb.arduinolibrarybuilder.controller.Controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
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
	 * Caminho dos recursos
	 */
	public static final String RESOURCES_PATH = "/resources/";
	
	/**
	 * Caminho dos ícones
	 */
	public static final String ICON_PATH = RESOURCES_PATH + "icon/";
	
	
	/*
	 * ===========================================================
	 * 			*** ATRIBUTOS ***
	 * ===========================================================
	 */
	
	/**
	 * Cena da janela principal
	 */
	private static Scene mainScene;
	
	
	/*
	 * ===========================================================
	 * 			*** M�TODOS PÚBLICOS ESTÁTICOS ***
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
	
	
	/**
	 * Recebe a janela principal da aplicação
	 * @return
	 */
	public static Stage getStage()
	{
		return mainScene != null ? (Stage) mainScene.getWindow() : null;
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
		Controller controller = loader.getController();
		InputStream icon = getClass().getResourceAsStream(ICON_PATH + "app.png");
		
		mainScene = new Scene(root);
		mainScene.getStylesheets().add(getClass().getResource("style/light.css").toExternalForm());
		
		if (icon != null)
			stage.getIcons().add(new Image(icon));		
		stage.setOnCloseRequest(ev -> controller.onCloseAction());
		stage.setResizable(false);
		stage.setScene(mainScene);
		stage.setTitle(APP_NAME);
		stage.show();
	}
}