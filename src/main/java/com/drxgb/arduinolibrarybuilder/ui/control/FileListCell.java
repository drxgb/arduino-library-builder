package com.drxgb.arduinolibrarybuilder.ui.control;

import java.io.File;

import com.drxgb.arduinolibrarybuilder.singleton.FileIconManager;

import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Representa a célula da lista que contém o ícone do arquivo e seu nome
 * tal como sua extensão
 */
public class FileListCell extends ListCell<String>
{
	/**
	 * 
	 * @see javafx.scene.control.Cell#updateItem(java.lang.Object, boolean)
	 */
	@Override
	public void updateItem(String item, boolean empty)
	{
		super.updateItem(item, empty);
		if (empty)
		{
			setGraphic(null);
			setText(null);
		}
		else
		{
			File file = new File(item);
			Image image = FileIconManager.getInstance().getImage(file);
			ImageView icon = new ImageView(image);
			
			if (file.isDirectory())
				item += "/";
			setGraphic(icon);
			setText(item);
		}
	}
}
