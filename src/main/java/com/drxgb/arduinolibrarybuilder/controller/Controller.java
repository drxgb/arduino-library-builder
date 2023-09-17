package com.drxgb.arduinolibrarybuilder.controller;

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
	 * Recebe a janela da visão atual
	 * @return Uma instância da janela atual
	 */
	public Stage getStage()
	{
		return root != null ? (Stage) root.getScene().getWindow() : null;
	}
}
