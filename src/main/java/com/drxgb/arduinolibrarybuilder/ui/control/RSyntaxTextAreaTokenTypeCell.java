package com.drxgb.arduinolibrarybuilder.ui.control;

import com.drxgb.arduinolibrarybuilder.util.RSyntaxTextAreaTokenType;

import javafx.scene.control.ListCell;

/**
 * 
 */
public class RSyntaxTextAreaTokenTypeCell extends ListCell<RSyntaxTextAreaTokenType>
{
	/*
	 * ===========================================================
	 * 			*** MÉTODOS PROTEGIDOS ***
	 * ===========================================================
	 */
	
	@Override
	protected void updateItem(RSyntaxTextAreaTokenType item, boolean empty)
	{
		super.updateItem(item, empty);
		if (!empty)
			setText(item != null ? item.toString() : "(none)");
		else
			setText(null);
	}
}
