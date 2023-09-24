package com.drxgb.arduinolibrarybuilder.service;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import com.drxgb.arduinolibrarybuilder.ArduinoLibraryBuilder;
import com.drxgb.arduinolibrarybuilder.io.FileStrategy;
import com.drxgb.arduinolibrarybuilder.io.KeywordsFileStrategy;
import com.drxgb.arduinolibrarybuilder.io.LibraryFileStrategy;
import com.drxgb.arduinolibrarybuilder.model.Keyword;
import com.drxgb.arduinolibrarybuilder.model.LibraryProperties;

/**
 * Realiza a construção do arquivo ZIP da biblioteca
 * 
 * @author Dr.XGB
 * @version 1.0.0
 */
public class ZipBuilder
{
	/*
	 * ===========================================================
	 * 			*** CONSTANTES ***
	 * ===========================================================
	 */
	
	private static final String ZIP_EXTENSION = ".zip";
	
	
	/*
	 * ===========================================================
	 * 			*** ATRIBUTOS ***
	 * ===========================================================
	 */
	
	private File outputDirectory;
	private File outputFile;
	
	
	/*
	 * ===========================================================
	 * 			*** MÉTODOS PÚBLICOS ***
	 * ===========================================================
	 */
	
	/**
	 * Define o diretório onde o ZIP será criado
	 * @param outputDirectory O caminho de saída
	 */
	public void setOutputDirectory(File outputDirectory)
	{
		this.outputDirectory = outputDirectory;
		this.outputFile = null;
	}
	
	
	/**
	 * Define o arquivo ZIP que será criado
	 * @param outputFile
	 */
	public void setOutputFile(String fileName)
	{
		StringBuilder sb = new StringBuilder();
		
		if (outputDirectory != null)
		{
			sb.append(outputDirectory.getAbsolutePath())
				.append(File.separatorChar);
		}
		
		sb.append(fileName);
		if (fileName.lastIndexOf(ZIP_EXTENSION) == -1)
				sb.append(ZIP_EXTENSION);
		
		outputFile = new File(sb.toString());
	}
	
	
	/**
	 * Recebe o arquivo ZIP a ser criado
	 * @return
	 */
	public File getOutputFile()
	{
		if (outputFile == null)
		{
			setOutputFile(
					outputDirectory != null 
						? outputDirectory.getName()
						: "output"
					+ ZIP_EXTENSION
			);
		}
		return outputFile;
	}
	
	
	/**
	 * Executa a construção do arquivo ZIP
	 * @param input O diretório de entrada
	 * @param props A instância das propriedades da biblioteca
	 * @param keywords A lista de instâncias de palavras-chave
	 * @throws IOException Quando ocorre um problema ao gravar o arquivo 
	 */
	public void execute(File input, LibraryProperties props, List<Keyword> keywords) throws IOException
	{
		if (outputDirectory == null)
			throw new RuntimeException("The output folder is not defined");
				
		final String LIB_PROPS = ArduinoLibraryBuilder.LIB_PROPS_FILE;
		final String KEYWORDS = ArduinoLibraryBuilder.KEYWORDS_FILE;
		File outputFile = getOutputFile();
		
		if (props != null)
			saveAdditionalFile(input, LIB_PROPS, new LibraryFileStrategy(props));		
		if (keywords != null)
			saveAdditionalFile(input, KEYWORDS, new KeywordsFileStrategy(keywords));
		
		try (ZipOutputStream output =
				new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(outputFile))))
		{
			compress(input, outputDirectory.getName(), output);
		}
	}
	
	
	/**
	 * Grava dados adicionais ao diretório de entrada
	 * @param input O diretório de entrada
	 * @param fileName Nome do novo arquivo
	 * @param strategy Método de gravação do arquivo
	 * @throws IOException Quando ocorre um erro durante a gravação do arquivo
	 */
	private void saveAdditionalFile(File input, String fileName, FileStrategy strategy) throws IOException
	{
		FileManager manager = new FileManager();
		File file = new File(input.getAbsolutePath() + File.separatorChar + fileName);
		
		manager.setStrategy(strategy);
		manager.save(file);
	}
	
	
	
	/**
	 * Comprime os arquivos para o arquivo ZIP de saída
	 * @param input O diretório de entrada
	 * @param fileName Nome do arquivo de saída
	 * @param output O novo arquivo ZIP a ser escrito
	 * @throws FileNotFoundException Quando o arquivo não for encontrado
	 * @throws IOException Quando ocorre um erro durante a gravação e/ou leitura do arquivo
	 */
	private void compress(File input, String fileName, ZipOutputStream output)
			throws FileNotFoundException, IOException
	{
		if (input.isHidden())
			return;
		
		if (input.isDirectory())
		{	
			File[] files = input.listFiles();			
			
			fileName += !fileName.endsWith(File.separator) ? File.separator : "";
			for (File target : files)
			{
				String targetPath = fileName + target.getName();
				compress(target, targetPath, output);
			}
		}
		else
		{
			try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(input)))
			{
				ZipEntry zipEntry = new ZipEntry(fileName);
				byte[] bytes = new byte[1024];
				int length;
				
				output.putNextEntry(zipEntry);
				while ((length = bis.read(bytes)) >= 0)
					output.write(bytes, 0, length);
			}
		}
	}
}
