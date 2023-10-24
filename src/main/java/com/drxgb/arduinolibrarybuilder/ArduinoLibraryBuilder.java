package com.drxgb.arduinolibrarybuilder;

import java.io.File;
import java.io.InputStream;
import java.util.Properties;

import com.drxgb.arduinolibrarybuilder.controller.Controller;
import com.drxgb.util.PropertiesManager;

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
	
	/**
	 * Arquivo de propriedades da biblioteca
	 */
	public static final String LIB_PROPS_FILE = "library.properties";
	
	/**
	 * Arquivo de palavras-chave
	 */
	public static final String KEYWORDS_FILE = "keywords.txt";
	
	/**
	 * Link da especificação da biblioteca
	 */
	public static final String LIB_SPECIFICATION_URL = "https://arduino.github.io/arduino-cli/dev/library-specification/";
	
	
	/*
	 * ===========================================================
	 * 			*** ATRIBUTOS ***
	 * ===========================================================
	 */
	
	/**
	 * A instância da aplicação
	 */
	private static ArduinoLibraryBuilder instance;
	
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
	
	
	/**
	 * Recebe a janela principal da aplicação
	 * @return
	 */
	public static Stage getStage()
	{
		return mainScene != null ? (Stage) mainScene.getWindow() : null;
	}
	
	
	/**
	 * Abre um link externo no navegador
	 * @param url O caminho do link externo
	 */
	public static void openExternalLink(String url)
	{
		instance.getHostServices().showDocument(url);
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
		Properties props = PropertiesManager.load(new File(Controller.PROPS_FILE));
		String style = props.getProperty(Controller.STYLE_KEY);
		
		instance = this;
		mainScene = new Scene(root);
		if (style != null)
			mainScene.getStylesheets().add(getClass().getResource(style).toExternalForm());
		
		if (icon != null)
			stage.getIcons().add(new Image(icon));		
		stage.setOnCloseRequest(ev -> controller.onCloseAction());
		stage.setResizable(true);
		stage.setScene(mainScene);
		stage.setTitle(APP_NAME);
		stage.show();
	}
}
