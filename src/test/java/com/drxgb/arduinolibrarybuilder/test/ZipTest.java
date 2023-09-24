package com.drxgb.arduinolibrarybuilder.test;

import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.drxgb.arduinolibrarybuilder.model.Keyword;
import com.drxgb.arduinolibrarybuilder.model.LibraryProperties;
import com.drxgb.arduinolibrarybuilder.service.ZipBuilder;
import com.drxgb.arduinolibrarybuilder.util.KeywordTokenType;
import com.drxgb.arduinolibrarybuilder.util.RSyntaxTextAreaTokenType;

/**
 * Realiza testes de compressão de arquivos
 * 
 * @author Dr.XGB
 * @version 1.0.0
 */
class ZipTest
{
	private static final String INPUT_DIR = "/input/";
	private static final String OUTPUT_DIR = "/output/";
	private static final String OUTPUT_FILE = "result.zip";


	@Test
	void test()
	{
		ZipBuilder builder = new ZipBuilder();
		File input = new File(getClass().getResource(INPUT_DIR).getPath());
		File output = new File(getClass().getResource(OUTPUT_DIR).getPath());
		LibraryProperties props = makeLibraryProperties();
		List<Keyword> keywords = makeKeywordsList();
		
		try
		{
			builder.setOutputDirectory(output);
			builder.setOutputFile(OUTPUT_FILE);
			builder.execute(input, props, keywords);
			System.out.println("Deu bom, clã!");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	
	private LibraryProperties makeLibraryProperties()
	{
		LibraryProperties props = new LibraryProperties();
		
		props.setName("Test Library");
		props.setVersion("1.0.0");
		props.setAuthorName("Dr.XGB");
		props.setAuthorEmail("dr.xgb.rm2k@gmail.com");
		props.setSentence("A sample library for tests");
		props.setCategory("Other");
		props.setUrl("https://drxgb.com/");
		props.setUseALinkage(true);
		props.setIncludes(Arrays.asList("lib1.h", "lib2.h", "lib3.h"));
		
		return props;
	}
	
	
	private List<Keyword> makeKeywordsList()
	{
		List<Keyword> keywords = new ArrayList<>();
		Keyword keyword1 = new Keyword();
		Keyword keyword2 = new Keyword();
		
		keyword1.setName("testType");
		keyword1.setTokenType(KeywordTokenType.KEYWORD1);
		keyword1.setReferenceLink("Zelda");
		keyword1.setRSyntaxTextAreaTokenType(RSyntaxTextAreaTokenType.DATA_TYPE);
		
		keyword2.setName("testMethod");
		keyword2.setTokenType(KeywordTokenType.KEYWORD2);
		keyword2.setReferenceLink("Link");
		keyword2.setRSyntaxTextAreaTokenType(RSyntaxTextAreaTokenType.RESERVED_WORD);
		
		keywords.add(keyword1);
		keywords.add(keyword2);
		
		return keywords;
	}
}
