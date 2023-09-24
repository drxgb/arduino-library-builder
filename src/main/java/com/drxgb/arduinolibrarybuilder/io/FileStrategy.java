package com.drxgb.arduinolibrarybuilder.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Responsável por realizar ações de gravação e leitura de um arquivo
 * 
 * @author Dr.XGB
 * @version 1.0.0
 */
public interface FileStrategy
{
	/**
	 * Carrega o arquivo para um conjunto de dados do sistema
	 * @param input
	 */
	void load(InputStream input) throws IOException;
	
	
	/**
	 * Salva os dados do sistema para um arquivo
	 * @param output
	 * @throws IOException 
	 */
	void save(OutputStream output) throws IOException;
	
	/**
	 * Recebe o resultado do arquivo em forma de dados
	 * @return O resultado do arquivo
	 */
	Object getResult();
}
