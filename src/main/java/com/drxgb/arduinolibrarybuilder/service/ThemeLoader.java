package com.drxgb.arduinolibrarybuilder.service;

import java.util.List;

import com.drxgb.arduinolibrarybuilder.model.Theme;

/**
 * Possui um contrato para que a classe precisa carregar uma lista
 * de temas de acrodo com o conteúdo contido dentro da pasta
 * 
 * @author Dr.XGB
 * @version 1.0.0
 */
public interface ThemeLoader
{
	/**
	 * Carrega um conjunto de temas através de arquivos fornecidos
	 * por um recurso
	 * @param path Caminho das folhas de estilo
	 * @return Uma lista de temas
	 */
	List<Theme> load(String path);
}
