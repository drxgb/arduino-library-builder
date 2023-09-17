package com.drxgb.arduinolibrarybuilder.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * Controlador da tela principal
 * @author Dr.XGB
 * @version 1.0.0
 */
public class MainController extends Controller
	implements Initializable
{
	/*
	 * ===========================================================
	 * 			*** ATRIBUTOS ***
	 * ===========================================================
	 */
	
	// Principal
	@FXML private Label lblSelectedPath;
	@FXML private Button btnBuildZip;
	@FXML private MenuItem mnitBuildZip;

	// File Structure
	@FXML private ListView<String> lstUnselectedFiles;
	@FXML private ListView<String> lstSelectedFiles;
	
	// Properties
	@FXML private Parent parPropertiesTab;
	@FXML private TextField txtName;
	@FXML private TextField txtVersion;
	@FXML private TextField txtAuthorName;
	@FXML private TextField txtAuthorEmail;
	@FXML private TextField txtMaintainerName;
	@FXML private TextField txtMaintainerEmail;
	@FXML private TextField txtSentence;
	@FXML private TextArea txtParagraph;
	@FXML private ComboBox<String> cbxCategory;
	@FXML private TextField txtUrl;
	@FXML private TextArea txtArchitectures;
	@FXML private TextArea txtDepends;
	@FXML private TextArea txtIncludes;
	@FXML private CheckBox chkUseALinkage;
	@FXML private ComboBox<String> cbxPrecompiled;
	@FXML private TextField txtLbFlags;
	
	// Keywords
	@FXML private Parent parKeywordsTab;
	@FXML private Parent parKeywords;
	

	/*
	 * ===========================================================
	 * 			*** MÉTODOS IMPLEMENTADOS ***
	 * ===========================================================
	 */	

	/**
	 * @see javafx.fxml.Initializable#initialize(java.net.URL, java.util.ResourceBundle)
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		// TODO Carregar dados da janela
	}
	
	
	/*
	 * ===========================================================
	 * 			*** AÇÕES DO CONTROLADOR ***
	 * ===========================================================
	 */
	
	// Principal
	// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
	
	/**
	 * Monta o pacote ZIP da biblioteca
	 */
	@FXML
	public void onBuildZipAction()
	{
		
	}
	
	
	/**
	 * Escolhe o caminho da pasta da biblioteca
	 */
	@FXML
	public void onOpenFolderAction()
	{
		
	}
	
	
	// Menu
	// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=	
	
	/**
	 * Seleciona a aba File Structure
	 */
	@FXML
	public void onFileStructureTabAction()
	{
		
	}
	
	
	/**
	 * Seleciona a aba Properties
	 */
	@FXML
	public void onPropertiesTabAction()
	{
		
	}
	
	
	/**
	 * Seleciona a aba Keywords
	 */
	@FXML
	public void onKeywordsTabAction()
	{
		
	}
	
	
	/**
	 * Seleciona o tema da interface gráfica
	 * @param ev Evento disparado do componente na janela
	 */
	public void onThemeAction(ActionEvent ev)
	{
		
	}
	
	
	/**
	 * Abre a página sobre criação de biblioteca
	 */
	@FXML
	public void onLibrarySpecificationAction()
	{
		
	}
	
	
	/**
	 * Abre a janela "Sobre"
	 */
	@FXML
	public void onAboutAction()
	{
		
	}
	
	
	// File Structure
	// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
	
	/**
	 * Inclui pasta ou arquivo ao pacote
	 */
	@FXML
	public void onAddAction()
	{
		
	}
	
	
	/**
	 * Inclui todos os arquivo ao pacote
	 */
	@FXML
	public void onAddAllAction()
	{
		
	}
	
	
	/**
	 * Retira pasta ou arquivo do pacote
	 */
	@FXML
	public void onRemoveAction()
	{
		
	}
	
	
	/**
	 * Retira todos os arquivos do pacote
	 */
	@FXML
	public void onRemoveAllAction()
	{
		
	}
	
	
	// Properties
	// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
	
	/**
	 * Marca opção de utilizar o arquivo library.properties na biblioteca
	 */
	@FXML
	public void onUsePropertiesFileAction()
	{
		
	}
	
	
	// Keywords
	// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
	
	/**
	 * Marca a opção de utilizar arquivo keywords.txt na biblioteca
	 */
	@FXML
	public void onUseKeywordFileAction()
	{
		
	}
	
	
	/**
	 * Inclui uma nova palavra-chave à lista
	 */
	@FXML
	public void onAddKeywordAction()
	{
		
	}
	
	
	/**
	 * Retira a palavra-chave da lista
	 * @param ev Evento disparado pelo elemento na janela
	 */
	public void onRemoveKeywordAction(ActionEvent ev)
	{
		
	}
}
