package com.drxgb.arduinolibrarybuilder.controller;

import java.io.IOException;
import java.net.URL;

import com.drxgb.arduinolibrarybuilder.ArduinoLibraryBuilder;
import com.drxgb.javafxutils.StageFactory;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.stage.Stage;

/**
 * Representa os controladores da aplicação
 * @author Dr.XGB
 * @version 1.0.0
 */
public abstract class Controller
{
	/*
	 * ===========================================================
	 * 			*** CONSTANTES ***
	 * ===========================================================
	 */
	
	/**
	 * Nome do arquivo de configurações
	 */
	public static final String PROPS_FILE = "settings.properties";
	
	/**
	 * Chave do último diretório carregado
	 */
	public static final String DIR_KEY = "dir";
	
	/**
	 * Chave do último estilo carregado
	 */
	public static final String STYLE_KEY = "style";
	
	/*
	 * ===========================================================
	 * 			*** ATRIBUTOS ***
	 * ===========================================================
	 */
	
	@FXML protected Parent root;

	
	/*
	 * ===========================================================
	 * 			*** AÇÕES DO CONTROLADOR ***
	 * ===========================================================
	 */
	
	/**
	 * Fecha a janela
	 */
	@FXML
	public void onCloseAction()
	{
		getStage().close();
	}
	
	
	/*
	 * ===========================================================
	 * 			*** MÉTODOS PÚBLICOS ***
	 * ===========================================================
	 */
	
	/**
	 * Cria uma janela de acordo com o arquivo FXML dado
	 * @param fxml O arquivo FXML para carregar a janela
	 * @param title O título da nova janela
	 * @return Uma instância da janela
	 * @throws IOException Quando o arquivo FXML não for encontrado
	 */
	public Stage makeWindow(String fxml, String title) throws IOException
	{
		Stage stage;
		Stage newStage;
		URL resource;
		StringBuilder sb;
		
		sb = new StringBuilder();
		sb.append("view/").append(fxml).append(".fxml");
		stage = getStage();
		resource = ArduinoLibraryBuilder.class.getResource(sb.toString());
		newStage = StageFactory.openWindow(stage, resource, title);
		newStage.getIcons().addAll(stage.getIcons());
		newStage.setResizable(false);
		
		return newStage;
	}
	
	/**
	 * Recebe a janela da visão atual
	 * @return Uma instância da janela atual
	 */
	public Stage getStage()
	{
		return root != null ? (Stage) root.getScene().getWindow() : null;
	}
}
