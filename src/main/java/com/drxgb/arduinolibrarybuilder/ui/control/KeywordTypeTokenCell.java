package com.drxgb.arduinolibrarybuilder.ui.control;

import com.drxgb.arduinolibrarybuilder.util.KeywordTokenType;

import javafx.scene.control.ListCell;

/**
 * Representa a célula que renderiza o campo KEYWORD_TOKENTYPE
 */
public final class KeywordTypeTokenCell extends ListCell<KeywordTokenType>
{
	/*
	 * ===========================================================
	 * 			*** MÉTODOS PROTEGIDOS ***
	 * ===========================================================
	 */
	
	@Override
	protected void updateItem(KeywordTokenType item, boolean empty)
	{
		super.updateItem(item, empty);
		if (!empty)
			setText(item.print());
		else
			setText(null);
	}
}
