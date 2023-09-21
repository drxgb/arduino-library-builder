package com.drxgb.arduinolibrarybuilder.ui;

import java.io.InputStream;

import com.drxgb.arduinolibrarybuilder.ArduinoLibraryBuilder;
import com.drxgb.arduinolibrarybuilder.controller.Controller;
import com.drxgb.arduinolibrarybuilder.model.Keyword;
import com.drxgb.arduinolibrarybuilder.ui.control.KeywordTypeTokenCell;
import com.drxgb.arduinolibrarybuilder.ui.control.RSyntaxTextAreaTokenTypeCell;
import com.drxgb.arduinolibrarybuilder.util.KeywordTokenType;
import com.drxgb.arduinolibrarybuilder.util.RSyntaxTextAreaTokenType;

import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * Responsável por gerar nós na janela
 * 
 * @author Dr.XGB
 * @version 1.0.0
 */
public class NodeFactory
{
	/*
	 * ===========================================================
	 * 			*** CONSTRUTORES ***
	 * ===========================================================
	 */
	
	public NodeFactory() {}
	
	
	/*
	 * ===========================================================
	 * 			*** MÉTODOS PÚBLICOS ***
	 * ===========================================================
	 */
	
	/**
	 * Cria os campos para as palavras-chave
	 * @param keyword
	 * @return
	 */
	public Parent makeKeywordPanel(Keyword keyword)
	{
		Pane parent;
		Pane parField;
		Button btnRemove;
		ImageView removeIcon;
		InputStream input;
		TextField txtField;
		ComboBox<KeywordTokenType> cbxKeywordTokenTypes;
		ComboBox<RSyntaxTextAreaTokenType> cbxRSyntaxTextAreaTokenTypes;
		ObservableList<Node> parentNodes;
		ObservableList<Node> fieldNodes;
		KeywordTokenType keywordTokenType;
		RSyntaxTextAreaTokenType rSyntaxTextAreaTokenType;

		parent = new HBox(12.0);
		parent.getProperties().put(Controller.KEYWORD_PROPS_KEY, keyword);
		parentNodes = parent.getChildren();
		
		// Botão Remover
		parField = makeKeywordField();
		input = ArduinoLibraryBuilder.class.getResourceAsStream("/resources/icon/remove.png");
		removeIcon = new ImageView(new Image(input));
		btnRemove = new Button();
		btnRemove.setGraphic(removeIcon);
		btnRemove.setOnAction(ev ->
		{
			Button btn = (Button) ev.getTarget();
			((Pane)parent.getParent()).getChildren().remove(btn.getParent().getParent());
		});
		fieldNodes = parField.getChildren();
		fieldNodes.add(btnRemove);
		parentNodes.add(parField);
		
		// KEYWORD
		parField = makeKeywordField();
		txtField = new TextField();
		txtField.textProperty()
			.addListener((obs, oldValue, newValue) -> keyword.setName(newValue));
		fieldNodes = parField.getChildren();
		fieldNodes.add(new Label("KEYWORD"));
		fieldNodes.add(txtField);
		parentNodes.add(parField);
		
		// KEYWORD_TOKENTYPE
		parField = makeKeywordField();
		keywordTokenType = keyword.getTokenType();
		cbxKeywordTokenTypes = new ComboBox<KeywordTokenType>();
		cbxKeywordTokenTypes.setItems(TokenTypeFields.keywordTokenTypes());
		cbxKeywordTokenTypes.valueProperty()
			.addListener((obs, oldValue, newValue) -> keyword.setTokenType(newValue));
		cbxKeywordTokenTypes.setButtonCell(new KeywordTypeTokenCell());
		cbxKeywordTokenTypes.setCellFactory(param -> new KeywordTypeTokenCell());
		
		if (keywordTokenType != null)
			cbxKeywordTokenTypes.getSelectionModel().select(keywordTokenType);
		else
			cbxKeywordTokenTypes.getSelectionModel().selectFirst();

		fieldNodes = parField.getChildren();
		fieldNodes.add(new Label("KEYWORD_TOKENTYPE"));
		fieldNodes.add(cbxKeywordTokenTypes);
		parentNodes.add(parField);
		
		// REFERENCE_LINK
		parField = makeKeywordField();
		txtField = new TextField();
		txtField.textProperty()
			.addListener((obs, oldValue, newValue) -> keyword.setReferenceLink(newValue));
		fieldNodes = parField.getChildren();
		fieldNodes.add(new Label("REFERENCE_LINK"));
		fieldNodes.add(txtField);
		parentNodes.add(parField);
		
		// RSYNTAXTEXTAREA_TOKENTYPE
		parField = makeKeywordField();
		rSyntaxTextAreaTokenType = keyword.getRSyntaxTextAreaTokenType();
		cbxRSyntaxTextAreaTokenTypes = new ComboBox<>();
		cbxRSyntaxTextAreaTokenTypes.setItems(TokenTypeFields.rSyntaxTextAreaTokenTypes());
		cbxRSyntaxTextAreaTokenTypes.valueProperty()
			.addListener((obs, oldValue, newValue) -> keyword.setRSyntaxTextAreaTokenType(newValue));
		cbxRSyntaxTextAreaTokenTypes.setButtonCell(new RSyntaxTextAreaTokenTypeCell());
		cbxRSyntaxTextAreaTokenTypes.setCellFactory(param -> new RSyntaxTextAreaTokenTypeCell());
		
		if (rSyntaxTextAreaTokenType != null)
			cbxRSyntaxTextAreaTokenTypes.getSelectionModel().select(rSyntaxTextAreaTokenType);
		else
			cbxRSyntaxTextAreaTokenTypes.getSelectionModel().selectFirst();

		fieldNodes = parField.getChildren();
		fieldNodes.add(new Label("RSYNTAXTEXTAREA_TOKENTYPE"));
		fieldNodes.add(cbxRSyntaxTextAreaTokenTypes);
		parentNodes.add(parField);
		
		return parent;
	}
	
	
	/**
	 * Cria o campo da palavra-chave
	 * @return
	 */
	public Pane makeKeywordField()
	{
		VBox field;
		
		field = new VBox(8.0);
		field.setAlignment(Pos.BOTTOM_LEFT);
		
		return field;
	}
}
