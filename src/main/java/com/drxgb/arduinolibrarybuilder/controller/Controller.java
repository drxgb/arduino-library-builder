package com.drxgb.arduinolibrarybuilder.controller;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.stage.Stage;

/**
 * Representa os controladores da aplica��o
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
	 * 			*** A��ES DO CONTROLADOR ***
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
	 * 			*** M�TODOS P�BLICOS ***
	 * ===========================================================
	 */
	
	/**
	 * Recebe a janela da vis�o atual
	 * @return Uma inst�ncia da janela atual
	 */
	public Stage getStage()
	{
		return root != null ? (Stage) root.getScene().getWindow() : null;
	}
}
