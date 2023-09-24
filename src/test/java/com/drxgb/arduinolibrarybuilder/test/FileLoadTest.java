package com.drxgb.arduinolibrarybuilder.test;

import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import com.drxgb.arduinolibrarybuilder.ArduinoLibraryBuilder;
import com.drxgb.arduinolibrarybuilder.io.KeywordsFileStrategy;
import com.drxgb.arduinolibrarybuilder.io.LibraryFileStrategy;
import com.drxgb.arduinolibrarybuilder.model.Keyword;
import com.drxgb.arduinolibrarybuilder.model.LibraryProperties;
import com.drxgb.arduinolibrarybuilder.service.FileManager;

/**
 * Realiza testes de carregamento de arquivos
 * 
 * @author Dr.XGB
 * @version 1.0.0
 */
class FileLoadTest
{
	private static final String INPUT_DIR = "/input/";
	

	@SuppressWarnings("unchecked")
	@Test
	void test()
	{
		try
		{
			final String LIB_PROPS = ArduinoLibraryBuilder.LIB_PROPS_FILE;
			final String KEYWORDS = ArduinoLibraryBuilder.KEYWORDS_FILE;
			
			File input = new File(getClass().getResource(INPUT_DIR).getPath());
			List<String> files = Arrays.asList(input.list());
			Optional<String> libPropsOptional = files.stream().filter(f -> f.equals(LIB_PROPS)).findFirst();
			Optional<String> keywordsOptional = files.stream().filter(f -> f.equals(KEYWORDS)).findFirst();			
			FileManager manager = new FileManager();
			
			if (libPropsOptional.isPresent())
			{			
				File file = new File(input.getAbsolutePath() + File.separator + libPropsOptional.get());
				
				manager.setStrategy(new LibraryFileStrategy(new LibraryProperties()));
				manager.load(file);
				System.out.println(manager.result());
			}
			
			if (keywordsOptional.isPresent())
			{
				List<Keyword> keywords;
				File file = new File(input.getAbsolutePath() + File.separator + keywordsOptional.get());
				
				manager.setStrategy(new KeywordsFileStrategy(new ArrayList<>()));
				manager.load(file);
				keywords = (List<Keyword>)manager.result();
				keywords.forEach(System.out::println);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
}
