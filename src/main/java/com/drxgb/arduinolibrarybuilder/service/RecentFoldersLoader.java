package com.drxgb.arduinolibrarybuilder.service;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import com.drxgb.util.PropertiesManager;

/**
 * Carrega e lista as pastas abertas recentemente
 * 
 * @author Dr.XGB
 * @version 1.0.0
 */
public class RecentFoldersLoader
{
	/*
	 * ===========================================================
	 * 			*** CONSTANTES ***
	 * ===========================================================
	 */
	
	/**
	 * Quantidade máxima de arquivos recentes
	 */
	private static final int MAX_SIZE = 10;
	
	/**
	 * Prefixo para a chave da pasta recente
	 */
	private static final String RECENT_KEY = "recent";
	
	/*
	 * ===========================================================
	 * 			*** ATRIBUTOS ***
	 * ===========================================================
	 */
	
	private List<String> recentFolders;
	
	
	/*
	 * ===========================================================
	 * 			*** CONSTRUTORES ***
	 * ===========================================================
	 */
	
	public RecentFoldersLoader()
	{
		recentFolders = new LinkedList<>();
	}
	
	
	/*
	 * ===========================================================
	 * 			*** MÉTODOS PÚBLICOS ***
	 * ===========================================================
	 */
	
	/**
	 * Carrega as pastas recentes através de um arquivo
	 * @param fileName Nome do arquivo que contém a lista
	 */
	public void load(String fileName)
	{
		File file = new File(fileName);
		Properties props = PropertiesManager.load(file);
		String key;
		String folder;
		
		recentFolders.clear();
		for (int i = 0;i < MAX_SIZE; ++i)
		{
			key = RECENT_KEY + i;
			folder = props.getProperty(key);
			
			if (folder != null)
				recentFolders.add(folder);
		}
	}
	
	
	/**
	 * Atualiza a lista de arqvuios recentes inserindo um novo diretório
	 * @param fileName Nome do arquivo que contém a lista
	 * @param path Diretório a ser inserido no arquivo
	 */
	public void save(String fileName, String path)
	{
		File file = new File(fileName);
		Properties props = PropertiesManager.load(file);
		String key;
		String folder;
		
		
		if (recentFolders.contains(path))
			recentFolders.remove(path);
		recentFolders.add(0, path);
		
		final int SIZE = recentFolders.size();
		
		for (int i = 0; i < SIZE && i < MAX_SIZE; ++i)
		{
			key = RECENT_KEY + i;
			folder = recentFolders.get(i);
			props.setProperty(key, folder);
		}
		
		PropertiesManager.save(file, props);
	}


	/*
	 * ===========================================================
	 * 			*** GETTERS E SETTERS ***
	 * ===========================================================
	 */
	
	public List<String> getRecentFolders()
	{
		return recentFolders;
	}	
}
