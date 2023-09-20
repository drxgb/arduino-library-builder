package com.drxgb.arduinolibrarybuilder.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.drxgb.arduinolibrarybuilder.ArduinoLibraryBuilder;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;

/**
 * Controlador da tela Sobre
 * 
 * @author Dr.XGB
 * @version 1.0.0
 */
public class AboutController extends Controller
	implements Initializable
{
	/*
	 * ===========================================================
	 * 			*** ATRIBUTOS ***
	 * ===========================================================
	 */
	
	@FXML private Label lblTitle;
	@FXML private Label lblVersion;
	@FXML private Label lblJavaVersion;
	@FXML private Label lblJavaFxVersion;
	
	
	/*
	 * ===========================================================
	 * 			*** MÉTODOS IMPLEMENTADOS ***
	 * ===========================================================
	 */

	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		lblTitle.setText(ArduinoLibraryBuilder.APP_NAME);
		lblVersion.setText(ArduinoLibraryBuilder.APP_VERSION);
		lblJavaVersion.setText(System.getProperty("java.version"));
		lblJavaFxVersion.setText(System.getProperty("javafx.runtime.version"));
	}
	
	
	/*
	 * ===========================================================
	 * 			*** AÇÕES DO CONTROLADOR ***
	 * ===========================================================
	 */
	
	/**
	 * Abre a página do autor do software
	 * @param ev
	 */
	@FXML
	public void onHlkAuthorSiteAction(ActionEvent ev)
	{
		Hyperlink link = (Hyperlink)ev.getTarget();
		ArduinoLibraryBuilder.openExternalLink(link.getText());
	}
}
