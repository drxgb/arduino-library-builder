package com.drxgb.arduinolibrarybuilder.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;

/**
 * Realiza a construção de um arquivo
 * 
 * @author Dr.XGB
 * @version 1.0.0
 */
public abstract class FileBuilder
{
	/*
	 * ===========================================================
	 * 			*** CONSTANTES ***
	 * ===========================================================
	 */
	
	private static final String TEMP_DIR = "XGB/ArduinoLibraryBuilder/input/";
	
	
	/*
	 * ===========================================================
	 * 			*** ATRIBUTOS ***
	 * ===========================================================
	 */
	
	protected File outputDirectory;
	protected File outputFile;
	
	
	/*
	 * ===========================================================
	 * 			*** MÉTODOS PÚBLICOS ***
	 * ===========================================================
	 */
	
	/**
	 * Cria uma pasta temporária com arquivos copiados
	 * @param children Arquivos a serem copiados
	 * @return O arquivo temporário criado
	 * @throws IOException Quando a criação do arquivo falhar
	 */
	public File createTempFile(List<File> children)
			throws IOException
	{		
		return createTempFile("tmp/", children);
	}
	
	
	/**
	 * Cria um pasta temporária vazia
	 * @return O arquivo temporário criado
	 * @throws IOException Quando a criação do arquivo falhar
	 */
	public File createTempFile()
			throws IOException
	{
		return createTempFile(null);
	}

	
	/**
	 * Cria um arquivo temporário em um caminho específico
	 * @param name Nome do arquivo temporário
	 * @param children Se o arquivo temporário for um diretório,
	 * incluir os arquivos dentro dele
	 * @return O arquivo temporário criado
	 * @throws IOException Quando a criação do arquivo falhar
	 */
	public File createTempFile(String name, List<File> children)
			throws IOException
	{
		File temp = makeTempDirectory(name);
		
		if (temp != null && children != null && temp.isDirectory())
		{
			for (File child : children)
			{
				File copy = new File(getTempFileName(temp, child));
				
				if (copy.isFile())
					FileUtils.copyFile(child, copy);
				else
					FileUtils.copyDirectory(child, copy);
			}
		}
		
		return temp;
	}
	
	
	/**
	 * Verifica se o diretório é temporário
	 * @param input O diretório a set testado
	 * @return Se o diretório é temporário
	 */
	public boolean isTemp(File input)
	{
		File tmp = input.getParentFile()
				.getParentFile()
				.getParentFile()
				.getParentFile();
		File javaTmpDir = new File(System.getProperty("java.io.tmpdir"));
		
		return tmp.equals(javaTmpDir);
	}
	
	
	/*
	 * ===========================================================
	 * 			*** GETTERS E SETTERS ***
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
		final String extension = getExtension();
		
		if (outputDirectory != null)
		{
			sb.append(outputDirectory.getAbsolutePath())
				.append(File.separatorChar);
		}
		
		sb.append(fileName);
		if (fileName.lastIndexOf(extension) == -1)
				sb.append(extension);
		
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
			StringBuilder sb = new StringBuilder();
			final String extension = getExtension();
			final String output = 
					outputDirectory != null ? outputDirectory.getName() : "output";
			
			sb.append(output);
			if (!output.endsWith(extension))
				sb.append(extension);			
			
			setOutputFile(sb.toString());
		}
		return outputFile;
	}
	
	
	/*
	 * ===========================================================
	 * 			*** MÉTODOS PRIVADOS ***
	 * ===========================================================
	 */	
	
	/**
	 * Recebe o nome do arquivo temporário
	 * @param dir Diretório temporário
	 * @param file O arquivo temporário
	 * @return O caminho absoluto da arquivo temporário.
	 */
	private String getTempFileName(File dir, File file)
	{
		StringBuilder sb = new StringBuilder();
		String path = dir.getAbsolutePath();			
		
		sb.append(path);		
		if (!path.endsWith(File.separator))
			sb.append(File.separatorChar);
		sb.append(file.getName());

		return sb.toString();		
	}
	
	
	/**
	 * Cria o diretório temporário
	 * @param subdirectory Subdiretório a ser incluído, se houver
	 * @return O diretório
	 */
	private File makeTempDirectory(String subdirectory)
	{
		StringBuilder sb = new StringBuilder();
		String baseTmpDir = System.getProperty("java.io.tmpdir");
		File input;
		
		sb.append(baseTmpDir).append(TEMP_DIR);
		if (subdirectory != null)
			sb.append(subdirectory);
		input = new File(sb.toString());
		
		if (!input.exists())
			input.mkdirs();
		
		return input;
	}
	
	
	/*
	 * ===========================================================
	 * 			*** MÉTODOS PÚBLICOS ABSTRATOS ***
	 * ===========================================================
	 */
	
	/**
	 * Recebe o nome da extensão do arquivo
	 * @return A extensão do arquivo
	 */
	public abstract String getExtension();
}
