package com.drxgb.arduinolibrarybuilder.model;

import java.util.List;

/**
 * Armazena os campos do arquivo library.properties
 * 
 * @author Dr.XGB
 * @version 1.0.0
 */
public class LibraryProperties
{
	/*
	 * ===========================================================
	 * 			*** ATRIBUTOS ***
	 * ===========================================================
	 */
	
	private String name;
	private String version;
	private String authorName;
	private String authorEmail;
	private String maintainerName;
	private String maintainerEmail;
	private String sentence;
	private String paragraph;
	private String category;
	private String url;
	private List<String> architectures;
	private List<String> depends;
	private List<String> includes;
	private boolean useALinkage;
	private String precompiled;
	private String ldFlags;
	
	
	/*
	 * ===========================================================
	 * 			*** CONSTRUTORES ***
	 * ===========================================================
	 */
	
	public LibraryProperties() {}
	
	
	/*
	 * ===========================================================
	 * 			*** GETTERS E SETTERS ***
	 * ===========================================================
	 */

	public String getName()
	{
		return name;
	}


	public String getVersion()
	{
		return version;
	}


	public String getAuthorName()
	{
		return authorName;
	}


	public String getAuthorEmail()
	{
		return authorEmail;
	}


	public String getMaintainerName()
	{
		return maintainerName;
	}


	public String getMaintainerEmail()
	{
		return maintainerEmail;
	}


	public String getSentence()
	{
		return sentence;
	}


	public String getParagraph()
	{
		return paragraph;
	}


	public String getCategory()
	{
		return category;
	}


	public String getUrl()
	{
		return url;
	}


	public List<String> getArchitectures()
	{
		return architectures;
	}


	public List<String> getDepends()
	{
		return depends;
	}


	public List<String> getIncludes()
	{
		return includes;
	}


	public boolean isUseALinkage()
	{
		return useALinkage;
	}


	public String getPrecompiled()
	{
		return precompiled;
	}


	public String getLdFlags()
	{
		return ldFlags;
	}


	public void setName(String name)
	{
		this.name = name;
	}


	public void setVersion(String version)
	{
		this.version = version;
	}


	public void setAuthorName(String authorName)
	{
		this.authorName = authorName;
	}


	public void setAuthorEmail(String authorEmail)
	{
		this.authorEmail = authorEmail;
	}


	public void setMaintainerName(String maintainerName)
	{
		this.maintainerName = maintainerName;
	}


	public void setMaintainerEmail(String maintainerEmail)
	{
		this.maintainerEmail = maintainerEmail;
	}


	public void setSentence(String sentence)
	{
		this.sentence = sentence;
	}


	public void setParagraph(String paragraph)
	{
		this.paragraph = paragraph;
	}


	public void setCategory(String category)
	{
		this.category = category;
	}


	public void setUrl(String url)
	{
		this.url = url;
	}


	public void setArchitectures(List<String> architectures)
	{
		this.architectures = architectures;
	}


	public void setDepends(List<String> depends)
	{
		this.depends = depends;
	}


	public void setIncludes(List<String> includes)
	{
		this.includes = includes;
	}


	public void setUseALinkage(boolean useALinkage)
	{
		this.useALinkage = useALinkage;
	}


	public void setPrecompiled(String precompiled)
	{
		this.precompiled = precompiled;
	}


	public void setLdFlags(String lbFlags)
	{
		this.ldFlags = lbFlags;
	}		

	
	/*
	 * ===========================================================
	 * 			*** TO STRING ***
	 * ===========================================================
	 */

	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		builder.append("LibraryProperties [\n\tname: ").append(name).append(",\n\tversion: ").append(version)
				.append(",\n\tauthorName: ").append(authorName).append(",\n\tauthorEmail: ").append(authorEmail)
				.append(",\n\tmaintainerName: ").append(maintainerName).append(",\n\tmaintainerEmail: ")
				.append(maintainerEmail).append(",\n\tsentence: ").append(sentence).append(",\n\tparagraph: ")
				.append(paragraph).append(",\n\tcategory: ").append(category).append(",\n\turl: ").append(url)
				.append(",\n\tarchitectures: ").append(architectures).append(",\n\tdepends: ").append(depends)
				.append(",\n\tincludes: ").append(includes).append(",\n\tuseALinkage: ").append(useALinkage)
				.append(",\n\tprecompiled: ").append(precompiled).append(",\n\tldFlags: ").append(ldFlags)
				.append("\n]");
		return builder.toString();
	}
}
