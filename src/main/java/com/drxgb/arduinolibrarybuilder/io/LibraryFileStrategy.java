package com.drxgb.arduinolibrarybuilder.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.drxgb.arduinolibrarybuilder.model.LibraryProperties;
import com.drxgb.arduinolibrarybuilder.util.LibraryPropertiesUtils;

/**
 * Define ações de gravação e leitura para o arquivo de
 * propriedades da biblioteca
 * 
 * @author Dr.XGB
 * @version 1.0.0
 */
public class LibraryFileStrategy implements FileStrategy
{
	/*
	 * ===========================================================
	 * 			*** CONSTANTES ***
	 * ===========================================================
	 */
	
	private static final String NAME = "name";
	private static final String VERSION = "version";
	private static final String AUTHOR = "author";
	private static final String MAINTAINER = "maintainer";
	private static final String SENTENCE = "sentence";
	private static final String PARAGRAPH = "paragraph";
	private static final String CATEGORY = "category";
	private static final String URL = "url";
	private static final String ARCHITECTURES = "architectures";
	private static final String DEPENDS = "depends";
	private static final String USE_A_LINKAGE = "use_a_linkage";
	private static final String INCLUDES = "includes";
	private static final String PRECOMPILED = "precomplied";
	private static final String LDFLAGS = "ldflags";
	
	private static final String EMAIL = "email";
	
	
	/*
	 * ===========================================================
	 * 			*** ATRIBUTOS ***
	 * ===========================================================
	 */
	
	private LibraryProperties libProps;

	
	/*
	 * ===========================================================
	 * 			*** CONSTRUTORES ***
	 * ===========================================================
	 */
	
	public LibraryFileStrategy(LibraryProperties props)
	{
		this.libProps = props;
	}


	/*
	 * ===========================================================
	 * 			*** MÉTODOS IMPLEMENTADOS ***
	 * ===========================================================
	 */
	
	/**
	 * @throws IOException 
	 * @see com.drxgb.arduinolibrarybuilder.io.FileStrategy#load(java.io.InputStream)
	 */
	@Override
	public void load(InputStream input) throws IOException
	{
		Properties props = new Properties();
		Map<String, String> author;
		Map<String, String> maintainer;
		List<String> architectures;
		List<String> depends;
		List<String> includes;
		
		props.load(input);
		author = LibraryPropertiesUtils.extractNameAndEmailProperty(props.getProperty(AUTHOR));
		maintainer = LibraryPropertiesUtils.extractNameAndEmailProperty(props.getProperty(MAINTAINER));
		architectures = LibraryPropertiesUtils.extractPropertyToList(props.getProperty(ARCHITECTURES));
		depends = LibraryPropertiesUtils.extractPropertyToList(props.getProperty(DEPENDS));
		includes = LibraryPropertiesUtils.extractPropertyToList(props.getProperty(INCLUDES));
		
		libProps.setName(props.getProperty(NAME));
		libProps.setVersion(props.getProperty(VERSION));
		libProps.setAuthorName(author.get(NAME));
		libProps.setAuthorEmail(author.get(EMAIL));
		libProps.setMaintainerName(maintainer.get(NAME));
		libProps.setMaintainerEmail(maintainer.get(EMAIL));
		libProps.setSentence(props.getProperty(SENTENCE));
		libProps.setParagraph(props.getProperty(PARAGRAPH));
		libProps.setCategory(props.getProperty(CATEGORY));
		libProps.setUrl(props.getProperty(URL));
		libProps.setArchitectures(architectures);
		libProps.setDepends(depends);
		libProps.setUseALinkage(Boolean.valueOf(props.getProperty(USE_A_LINKAGE)));
		libProps.setIncludes(includes);
		libProps.setPrecompiled(props.getProperty(PRECOMPILED));
		libProps.setLdFlags(props.getProperty(LDFLAGS));
	}


	/**
	 * @throws IOException 
	 * @see com.drxgb.arduinolibrarybuilder.io.FileStrategy#save(java.io.OutputStream)
	 */
	@Override
	public void save(OutputStream output) throws IOException
	{
		Properties props = new Properties();
		String author = LibraryPropertiesUtils.makeNameAndEmailProperty(
				libProps.getAuthorName(),
				libProps.getAuthorEmail()
		);
		String maintainer = LibraryPropertiesUtils.makeNameAndEmailProperty(
				libProps.getMaintainerName(),
				libProps.getMaintainerEmail()
		);
		String architectures = LibraryPropertiesUtils.makePropertyFromList(libProps.getArchitectures());
		String depends = LibraryPropertiesUtils.makePropertyFromList(libProps.getDepends());
		String includes = LibraryPropertiesUtils.makePropertyFromList(libProps.getIncludes());
		
		putStringProperty(props, NAME, libProps.getName());
		putStringProperty(props, VERSION, libProps.getVersion());
		putStringProperty(props, AUTHOR, author);
		putStringProperty(props, MAINTAINER, maintainer);
		putStringProperty(props, SENTENCE, libProps.getSentence());
		putStringProperty(props, PARAGRAPH, libProps.getParagraph());
		putStringProperty(props, CATEGORY, libProps.getCategory());
		putStringProperty(props, URL, libProps.getUrl());
		putStringProperty(props, ARCHITECTURES, architectures);
		putStringProperty(props, DEPENDS, depends);
		putStringProperty(props, USE_A_LINKAGE, String.valueOf(libProps.isUseALinkage()));
		putStringProperty(props, INCLUDES, includes);
		putStringProperty(props, PRECOMPILED, libProps.getPrecompiled());
		putStringProperty(props, LDFLAGS, libProps.getLdFlags());
		
		props.store(output, "Auto-generated by XGB's Arduino Library Builder");
	}
	
	
	/**
	 * @see com.drxgb.arduinolibrarybuilder.io.FileStrategy#getResult()
	 */
	@Override
	public Object getResult()
	{		
		return libProps;
	}


	/*
	 * ===========================================================
	 * 			*** MÉTODOS PRIVADOS ***
	 * ===========================================================
	 */
	
	/**
	 * Insere um valor de texto a uma chave de propriedades
	 * @param props
	 * @param key
	 * @param value
	 */
	private void putStringProperty(Properties props, String key, String value)
	{
		if (value != null)
			props.setProperty(key, value);
	}
}
