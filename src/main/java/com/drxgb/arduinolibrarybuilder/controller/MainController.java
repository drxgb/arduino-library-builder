package com.drxgb.arduinolibrarybuilder.controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.util.ResourceBundle;

import com.drxgb.arduinolibrarybuilder.ArduinoLibraryBuilder;
import com.drxgb.arduinolibrarybuilder.model.Keyword;
import com.drxgb.arduinolibrarybuilder.model.LibraryProperties;
import com.drxgb.arduinolibrarybuilder.model.Theme;
import com.drxgb.arduinolibrarybuilder.service.RecentFoldersLoader;
import com.drxgb.arduinolibrarybuilder.service.ThemeLoader;
import com.drxgb.arduinolibrarybuilder.service.ThemeService;
import com.drxgb.arduinolibrarybuilder.service.ZipBuilder;
import com.drxgb.arduinolibrarybuilder.ui.NodeFactory;
import com.drxgb.arduinolibrarybuilder.ui.control.FileListCell;
import com.drxgb.arduinolibrarybuilder.util.LibraryPropertiesUtils;
import com.drxgb.arduinolibrarybuilder.util.SortDirectory;
import com.drxgb.arduinolibrarybuilder.util.SortFileList;
import com.drxgb.javafxutils.DialogBuilder;
import com.drxgb.util.PropertiesManager;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Controlador da tela principal
 * @author Dr.XGB
 * @version 1.0.0
 */
public class MainController extends Controller
	implements Initializable
{	
	/*
	 * ===========================================================
	 * 			*** ATRIBUTOS ***
	 * ===========================================================
	 */
	
	// Principal
	@FXML private Label lblSelectedPath;
	@FXML private Button btnBuildZip;
	@FXML private TabPane tabMain;
	@FXML private MenuItem mnitBuildZip;
	@FXML private Menu mnuOpenRecent;
	@FXML private Menu mnuThemes;

	// File Structure
	@FXML private ListView<String> lstUnselectedFiles;
	@FXML private ListView<String> lstSelectedFiles;
	@FXML private Button btnAdd;
	@FXML private Button btnAddAll;
	@FXML private Button btnRemove;
	@FXML private Button btnRemoveAll;
	
	// Properties
	@FXML private Parent parPropertiesTab;
	@FXML private TextField txtName;
	@FXML private TextField txtVersion;
	@FXML private TextField txtAuthorName;
	@FXML private TextField txtAuthorEmail;
	@FXML private TextField txtMaintainerName;
	@FXML private TextField txtMaintainerEmail;
	@FXML private TextField txtSentence;
	@FXML private TextArea txtParagraph;
	@FXML private ComboBox<String> cbxCategory;
	@FXML private TextField txtUrl;
	@FXML private TextArea txtArchitectures;
	@FXML private TextArea txtDepends;
	@FXML private TextArea txtIncludes;
	@FXML private CheckBox chkUseALinkage;
	@FXML private ComboBox<String> cbxPrecompiled;
	@FXML private TextField txtLdFlags;
	
	// Keywords
	@FXML private Parent parKeywordsTab;
	@FXML private VBox parKeywords;
	
	// Atributos do controlador
	private File selectedPath;
	
	
	/*
	 * ===========================================================
	 * 			*** ASSOCIAÇÕES ***
	 * ===========================================================
	 */

	private NodeFactory nodeFactory;
	private RecentFoldersLoader recentFoldersLoader;
	private ThemeService themeService;
	
	
	/*
	 * ===========================================================
	 * 			*** CONSTRUTORES ***
	 * ===========================================================
	 */
	
	public MainController()
	{
		super();
		recentFoldersLoader = new RecentFoldersLoader();
		themeService = new ThemeService();
		nodeFactory = new NodeFactory();
	}
	

	/*
	 * ===========================================================
	 * 			*** MÉTODOS IMPLEMENTADOS ***
	 * ===========================================================
	 */	

	/**
	 * @see javafx.fxml.Initializable#initialize(java.net.URL, java.util.ResourceBundle)
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		loadThemes();
		loadRecentFolders();
		setTooltipForTextAreas();
		setCellFactoryToListView(lstUnselectedFiles);
		setCellFactoryToListView(lstSelectedFiles);
		loadCategoryField();
		loadPrecompiledField();
	}	


	/*
	 * ===========================================================
	 * 			*** AÇÕES DO CONTROLADOR ***
	 * ===========================================================
	 */
	
	// Principal
	// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
	
	/**
	 * Monta o pacote ZIP da biblioteca
	 */
	@FXML
	public void onBuildZipAction()
	{
		ZipBuilder builder = new ZipBuilder();
		DirectoryChooser chooser = new DirectoryChooser();
		File settings = new File(PROPS_FILE);
		Properties props = PropertiesManager.load(settings); 
		File outputDir = new File(props.getProperty(OUTPUT_KEY));		
		
		chooser.setInitialDirectory(outputDir);
		outputDir = chooser.showDialog(getStage());
		
		if (outputDir != null)
		{
			try
			{
				List<File> selectedFiles = makeSelectedFilesToBuild();
				LibraryProperties libProperties = makeLibraryProperties();
				List<Keyword> keywords = makeKeywords();

				props.setProperty(OUTPUT_KEY, outputDir.getAbsolutePath());
				PropertiesManager.save(settings, props);
				
				buildLibraryProperties(libProperties);
				builder.setOutputDirectory(outputDir);
				builder.setOutputFile(buildOutputZipFileName());
				builder.execute(selectedPath.getName(), selectedFiles, libProperties, keywords);
				DialogBuilder.show(getStage(), AlertType.INFORMATION, "GG", "ZIP was built successfully!");
			}
			catch (Exception e)
			{
				e.printStackTrace();
				DialogBuilder.show(getStage(), AlertType.ERROR, "Build ZIP failed", e.getMessage());
			}
		}
	}
	
	
	/**
	 * Escolhe o caminho da pasta da biblioteca
	 */
	@FXML
	public void onOpenFolderAction()
	{
		String path;
		File directory;
		File settings = new File(PROPS_FILE);
		DirectoryChooser chooser = new DirectoryChooser();
		Properties props = PropertiesManager.load(settings); 
		
		path = props.getProperty(INPUT_KEY);
		if (path != null)
			chooser.setInitialDirectory(new File(path));
		
		chooser.setTitle("Choose an Arduino Library root folder");
		directory = chooser.showDialog(getStage());
		
		if (directory != null)
		{
			path = directory.getAbsolutePath();
			openFolder(path);
		}
	}
	
	
	// Menu
	// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=	
	
	/**
	 * Seleciona a aba File Structure
	 */
	@FXML
	public void onFileStructureTabAction()
	{
		if (!tabMain.isDisable())
			tabMain.getSelectionModel().select(0);
	}
	
	
	/**
	 * Seleciona a aba Properties
	 */
	@FXML
	public void onPropertiesTabAction()
	{
		if (!tabMain.isDisable())
			tabMain.getSelectionModel().select(1);
	}
	
	
	/**
	 * Seleciona a aba Keywords
	 */
	@FXML
	public void onKeywordsTabAction()
	{
		if (!tabMain.isDisable())
			tabMain.getSelectionModel().select(2);
	}
	
	
	/**
	 * Seleciona o tema da interface gráfica
	 * @param ev Evento disparado do componente na janela
	 */
	public void onThemeAction(ActionEvent ev)
	{
		File settings = new File(PROPS_FILE);
		Properties props = PropertiesManager.load(settings);
		MenuItem menuItem = (MenuItem)ev.getTarget();
		String styleName = menuItem.getText();
		Optional<Theme> optional = themeService.getThemes()
				.stream()
				.filter(t -> t.getName().equals(styleName))
				.findFirst();
		Theme theme;
		
		if (optional.isPresent())
		{
			theme = optional.get();
			final int INDEX = themeService.getThemes().indexOf(theme);
			
			themeService.setCurrentTheme(INDEX);
			themeService.applyTheme(root.getScene());
			props.setProperty(STYLE_KEY, theme.getPath());
			PropertiesManager.save(settings, props);
		}
	}
	
	
	/**
	 * Abre a página sobre criação de biblioteca
	 */
	@FXML
	public void onLibrarySpecificationAction()
	{
		String uri = ArduinoLibraryBuilder.LIB_SPECIFICATION_URL;
		ArduinoLibraryBuilder.openExternalLink(uri);
	}
	
	
	/**
	 * Abre a janela "Sobre"
	 * @throws IOException 
	 */
	@FXML
	public void onAboutAction() throws IOException
	{
		Stage aboutStage = makeWindow("AboutView", "About");		

		aboutStage.initModality(Modality.WINDOW_MODAL);
		aboutStage.showAndWait();
	}
	
	
	// File Structure
	// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
	
	/**
	 * Inclui pasta ou arquivo ao pacote
	 */
	@FXML
	public void onAddAction()
	{
		ObservableList<String> unselectedItems = lstUnselectedFiles.getItems();
		ObservableList<String> selectedItems = lstSelectedFiles.getItems();
		final int index = lstUnselectedFiles.selectionModelProperty().get().getSelectedIndex();
		String fileName;
		
		if (index != -1)
		{
			fileName = unselectedItems.get(index);
			selectedItems.add(fileName);
			unselectedItems.remove(index);
			updateList(unselectedItems);
			updateList(selectedItems);
			updateZipButtonState();
			updateFileStructureButtons();
		}
	}
	
	
	/**
	 * Inclui todos os arquivo ao pacote
	 */
	@FXML
	public void onAddAllAction()
	{
		ObservableList<String> unselectedItems = lstUnselectedFiles.getItems();
		ObservableList<String> selectedItems = lstSelectedFiles.getItems();
		
		selectedItems.addAll(unselectedItems);
		unselectedItems.clear();
		updateList(selectedItems);
		updateZipButtonState();
		updateFileStructureButtons();
	}
	
	
	/**
	 * Retira pasta ou arquivo do pacote
	 */
	@FXML
	public void onRemoveAction()
	{
		ObservableList<String> unselectedItems = lstUnselectedFiles.getItems();
		ObservableList<String> selectedItems = lstSelectedFiles.getItems();
		final int index = lstSelectedFiles.selectionModelProperty().get().getSelectedIndex();
		String fileName;
		
		if (index != -1)
		{
			fileName = selectedItems.get(index);		
			selectedItems.remove(index);
			unselectedItems.add(fileName);
			updateList(unselectedItems);
			updateList(selectedItems);
			updateZipButtonState();
			updateFileStructureButtons();
		}
	}
	
	
	/**
	 * Retira todos os arquivos do pacote
	 */
	@FXML
	public void onRemoveAllAction()
	{
		ObservableList<String> unselectedItems = lstUnselectedFiles.getItems();
		ObservableList<String> selectedItems = lstSelectedFiles.getItems();
		
		unselectedItems.addAll(selectedItems);
		selectedItems.clear();
		updateList(unselectedItems);
		updateZipButtonState();
		updateFileStructureButtons();
	}
	
	
	// Properties
	// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
	
	/**
	 * Marca opção de utilizar o arquivo library.properties na biblioteca
	 */
	@FXML
	public void onUsePropertiesFileAction()
	{
		final boolean disable = parPropertiesTab.isDisable();
		parPropertiesTab.setDisable(!disable);
	}
	
	
	// Keywords
	// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
	
	/**
	 * Marca a opção de utilizar arquivo keywords.txt na biblioteca
	 */
	@FXML
	public void onUseKeywordFileAction()
	{
		final boolean disable = parKeywordsTab.isDisable();
		parKeywordsTab.setDisable(!disable);
	}
	
	
	/**
	 * Inclui uma nova palavra-chave à lista
	 */
	@FXML
	public void onAddKeywordAction()
	{
		Parent panel = nodeFactory.makeKeywordPanel(new Keyword());	
		parKeywords.getChildren().add(panel);
	}
	
	
	/*
	 * ===========================================================
	 * 			*** MÉTODOS PRIVADOS ***
	 * ===========================================================
	 */
	
	
	/**
	 * Insere dicas de uma opção por linha para os devidos campos
	 */
	private void setTooltipForTextAreas()
	{
		final Tooltip tooltip = new Tooltip("One option per line");
		
		txtArchitectures.setTooltip(tooltip);
		txtDepends.setTooltip(tooltip);
		txtIncludes.setTooltip(tooltip);
	}
	
	
	/**
	 * Monta a lista do subdiretório da pasta selecionada
	 * @param directory Caminho raiz selecionado
	 */
	private void loadFileStructure(File directory)
	{		
		if (directory.isDirectory())
		{
			ObservableList<String> items = lstUnselectedFiles.getItems();
			File[] files = directory.listFiles();
			String name;
			
			items.clear();
			lstSelectedFiles.getItems().clear();
			Arrays.sort(files, new SortDirectory());

			for (File child : files)
			{
				name = child.getName();
				items.add(name);
			}
		}
	}
	
	
	/**
	 * Define a fábrica de células para uma lista com o padrão dos arquivos
	 * @param listView
	 */
	private void setCellFactoryToListView(ListView<String> listView)
	{
		listView.setCellFactory((ListView<String> list) -> new FileListCell());
	}
	
	
	/**
	 * Atualiza a lista de células
	 * @param list
	 */
	private void updateList(ObservableList<String> list)
	{
		list.sort(new SortFileList());
	}
	
	
	/**
	 * Atualiza os estados dos botões de montar ZIP de acordo
	 * com o tamanho da lista de arquivos selecionados
	 */
	private void updateZipButtonState()
	{
		final boolean disable = lstSelectedFiles.getItems().isEmpty();
		
		btnBuildZip.setDisable(disable);
		mnitBuildZip.setDisable(disable);
	}
	
	
	/**
	 * Atualiza os botões de ação das listas de arquivos
	 */
	private void updateFileStructureButtons()
	{
		final boolean addDisabled = lstUnselectedFiles.getItems().isEmpty();
		final boolean removeDisabled = lstSelectedFiles.getItems().isEmpty();
		
		btnAdd.setDisable(addDisabled);
		btnAddAll.setDisable(addDisabled);
		btnRemove.setDisable(removeDisabled);
		btnRemoveAll.setDisable(removeDisabled);
	}
	
	
	/**
	 * Carrega os temas e adiciona ao menu
	 */
	private void loadThemes()
	{
		File dir = new File(ArduinoLibraryBuilder.class.getResource("style/").getPath()); 
		ObservableList<MenuItem> items = mnuThemes.getItems();
		List<Theme> themes = ThemeLoader.loadFromFolder(dir);
		RadioMenuItem item;
		ToggleGroup toggleGroup = new ToggleGroup();
		Theme theme;
		int initialIndex;
		
		items.clear();
		for (int i = 0; i < themes.size(); ++i)
		{
			theme = themes.get(i);
			item = new RadioMenuItem(theme.getName());
			item.setToggleGroup(toggleGroup);
			item.setOnAction(ev -> onThemeAction(ev));
			items.add(item);
		}		
		themeService.setThemes(themes);
		initialIndex = getLastStyleIndex();
		item = (RadioMenuItem)mnuThemes.getItems().get(initialIndex);
		item.setSelected(true);
	}
	
	
	/**
	 * Encontra o índice do último estilo carregado anteriormente
	 * @return
	 */
	private int getLastStyleIndex()
	{
		File settings = new File(PROPS_FILE);
		Properties props = PropertiesManager.load(settings);
		Theme theme;
		int index;
		final String style = props.getProperty(STYLE_KEY);
		
		index = 0;
		
		if (style != null)
		{
			Optional<Theme> result;
			result = themeService.getThemes()
					.stream()
					.filter(t -> t.getPath().equals(style))
					.findFirst();
			
			if (result.isPresent())
			{
				theme = result.get();
				index = themeService.getThemes().indexOf(theme);
				
				if (index == -1)
					index = 0;
			}
		}
		
		return index;
	}
	
	
	/**
	 * Carrega os arquivos recentes
	 */
	private void loadRecentFolders()
	{
		List<String> folders;
		ObservableList<MenuItem> items;
		MenuItem item;
		Label label;
		int index = 0;
		
		recentFoldersLoader.load(PROPS_FILE);
		folders = recentFoldersLoader.getRecentFolders();
		items = mnuOpenRecent.getItems();
		items.clear();
		
		for (String folder : folders)
		{			
			label = new Label("" + (index + 1) + ":");
			item = new MenuItem(folder, label);
			item.setOnAction(ev -> {
				MenuItem source = (MenuItem)ev.getSource();
				String dir = source.getText();
				
				openFolder(dir);
			});
			items.add(item);
			++index;
		}
	}
	
	
	/**
	 * Abre a pasta e carrega o prgorama com os dados da pasta
	 * @param dir
	 */
	private void openFolder(String dir)
	{
		File settings = new File(PROPS_FILE);
		Properties props = PropertiesManager.load(settings);
		
		selectedPath = new File(dir);
		lblSelectedPath.setText(dir);
		tabMain.setDisable(false);
		loadFileStructure(new File(dir));
		updateFileStructureButtons();
		props.setProperty(INPUT_KEY, dir);
		PropertiesManager.save(settings, props);
		recentFoldersLoader.save(PROPS_FILE, dir);
		loadRecentFolders();
	}


	/**
	 * Carrega o campo Category na aba Properties
	 */
	private void loadCategoryField()
	{
		ObservableList<String> categories = cbxCategory.getItems();
		
		categories.add("Uncategorized");
		categories.add("Display");
		categories.add("Communication");
		categories.add("Signal Input/Output");
		categories.add("Sensors");
		categories.add("Device Control");
		categories.add("Timing");
		categories.add("Data Storage");
		categories.add("Data Processing");
		categories.add("Other");
		cbxCategory.getSelectionModel().selectFirst();
	}
	
	
	/**
	 * Carrega o campo Precompiled na aba Properties
	 */
	private void loadPrecompiledField()
	{
		ObservableList<String> options = cbxPrecompiled.getItems();
		
		options.add("none");
		options.add("true");
		options.add("full");
		cbxPrecompiled.getSelectionModel().selectFirst();
	}
	
	
	/**
	 * Monta as propriesdades de biblioteca de acordo com os campos
	 * da janela
	 * @param props A instância das propriedades a ser escrita
	 */
	private void buildLibraryProperties(LibraryProperties props)
	{
		if (props != null)
		{
			final String SEP = "\\n";
			
			props.setName(txtName.getText());
			props.setVersion(txtVersion.getText());
			props.setAuthorName(txtAuthorName.getText());
			props.setAuthorEmail(txtAuthorEmail.getText());
			props.setMaintainerName(txtMaintainerName.getText());
			props.setMaintainerEmail(txtMaintainerEmail.getText());
			props.setSentence(txtSentence.getText());
			props.setParagraph(txtParagraph.getText());
			props.setCategory(cbxCategory.getSelectionModel().getSelectedItem());
			props.setUrl(txtUrl.getText());
			props.setArchitectures(Arrays.asList(txtArchitectures.getText().split(SEP)));
			props.setDepends(Arrays.asList(txtDepends.getText().split(SEP)));
			props.setIncludes(Arrays.asList(txtIncludes.getText().split(SEP)));
			props.setUseALinkage(chkUseALinkage.isSelected());
			props.setPrecompiled(cbxPrecompiled.getSelectionModel().getSelectedItem());
			props.setLdFlags(txtLdFlags.getText());
		}
	}
	
	
	/**
	 * Cria o nome do arquivo de saída
	 * @return O nome do arquivo ZIP de saída a ser criado
	 */
	private String buildOutputZipFileName()
	{
		StringBuilder sb = new StringBuilder();
		
		sb.append(selectedPath.getName()).append(".zip");
		return sb.toString();
	}
	
	
	/**
	 * Cria uma instância de <code>LibraryProperties</code> com os
	 * campos da interface gráfica
	 * @return Uma instância de <code>LibraryProperties</code>
	 */
	private LibraryProperties makeLibraryProperties()
	{
		if (parKeywordsTab.isDisable())
			return null;
		
		LibraryProperties props = new LibraryProperties();
		
		props.setName(txtName.getText());
		props.setVersion(txtVersion.getText());
		props.setAuthorName(txtAuthorName.getText());
		props.setAuthorEmail(txtAuthorEmail.getText());
		props.setMaintainerName(txtMaintainerName.getText());
		props.setMaintainerEmail(txtMaintainerEmail.getText());
		props.setSentence(txtSentence.getText());
		props.setParagraph(txtParagraph.getText());
		props.setCategory(cbxCategory.getValue());
		props.setUrl(txtUrl.getText());
		props.setArchitectures(LibraryPropertiesUtils.extractPropertyToList(txtArchitectures.getText()));
		props.setDepends(LibraryPropertiesUtils.extractPropertyToList(txtDepends.getText()));
		props.setIncludes(LibraryPropertiesUtils.extractPropertyToList(txtIncludes.getText()));
		props.setUseALinkage(chkUseALinkage.isSelected());
		props.setPrecompiled(cbxPrecompiled.getValue());
		props.setLdFlags(txtLdFlags.getText());
		
		return props;
	}
	
	
	/**
	 * Cria uma lista de instâncias de <code>Keyword</code> com os
	 * campos da interface gráfica
	 * @return Uma lista de palavras-chave
	 */
	private List<Keyword> makeKeywords()
	{
		if (parKeywordsTab.isDisable())
			return null;
		
		return parKeywords.getChildren()
				.stream()
				.map(child -> (Keyword)child.getProperties().get(Controller.KEYWORD_PROPS_KEY))
				.toList();
	}
	
	
	/**
	 * Cria uma lista de arquivos a serem gravados em ZIP
	 * @return A lista de arquivos
	 */
	private List<File> makeSelectedFilesToBuild()
	{
		return lstSelectedFiles.getItems()
			.stream()
			.map(item -> new File(selectedPath + File.separator + item))
			.toList();
	}
}
