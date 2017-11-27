package view;


import java.util.ArrayList;
import java.util.Optional;

import common.NotNull;
import controller.ReadController;
import controller.WriteController;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.ToolBar;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * @author Team15
 * Responsible for generating Map editor view
 */
public class MapEditorView implements IView{		

	
	/**
	 * A {@link Scene} object that has  UI
	 */
	private static Scene editorScene = null;
	
	/**
	 * String that stores current selected continent and country
	 */
	private static String country,continent = null;
	
	
	/**
	 * read and write updates only through Controllers
	 */
	private ReadController readController = null;
	private WriteController writeController = null;
	
	/**
	 * Flags to check if a continent or country is deleted
	 */
	boolean isDeletedContinent= false,isDeletedContry=false;
	ArrayList<ComboBox<String>> playerList = new ArrayList<>();
	TextInputDialog dialog = new TextInputDialog();
	private Button startGameButton = new Button();
	private Button closeButton = new Button();


	/**
	 * Constructor used to inject controller dependencies
	 * @param new_readController, get called to do read operations 
	 * @param new_writeController, get called to do write operations
	 */
	public MapEditorView(ReadController new_readController,WriteController new_writeController) {
		readController = new_readController;
		writeController = new_writeController;
	}


	/**
	 * Returns Scene object a container having Map Editor UI elements
	 * @return {@link Scene} instance
	 * @see view.IView#getView()
	 */
	@NotNull
	public Scene getView(boolean isResume){
		
		//We use ObservableList in order to attach listeners to choice box
		ObservableList<String> continents = FXCollections.observableArrayList();		 
		ObservableList<String> contries = FXCollections.observableArrayList();
		continents.addAll(readController.getAllContinentNames());
		//ObservableList for selecting player strategies
		ObservableList<String> options = 
			    FXCollections.observableArrayList(
			        "Human",
			        "Aggressive",
			        "Benevolent",
			        "Random",
			        "Cheater"
			    );
		
		//Entire screen uses grid layout
		GridPane gridPane = new GridPane();
		gridPane.setHgap(10);
		gridPane.setVgap(10);

		//holds all players input section
		VBox playerStratagiesContiner = new VBox();
		
		ChoiceBox<String> continentChoiceBox = new ChoiceBox<String>();
		ChoiceBox<String> countriesChoiceBox = new ChoiceBox<String>();
		
		Button addContent = new Button("Add Continent");	     
		Button addCountry = new Button("Add Country");	     
		Button deleteContent = new Button("Delete Continent");	     
		Button deleteCountry = new Button("Delete Country");
		Button addMorePlayers = new Button("Add Players");
		Button saveChanges = new Button("Save Changes");

		
		TextField editadjacentContries = new TextField ();
		editadjacentContries.setPrefWidth(800);
		editadjacentContries.setPromptText("Adjacent Countries");
		TextField editContinentValue = new TextField ();
		editContinentValue.setPromptText("Continent Value");

		addContent.setOnAction(new EventHandler<ActionEvent>() {			
			@Override
			public void handle(ActionEvent event) {
				String tmp = getUserInput("Enter continent name");
				continentChoiceBox.getItems().add(tmp);
				continentChoiceBox.setValue(tmp);
			}
		});

		deleteContent.setOnAction(new EventHandler<ActionEvent>() {			
			@Override
			public void handle(ActionEvent event) {
				isDeletedContinent = true;
			}
		}); 


		deleteCountry.setOnAction(new EventHandler<ActionEvent>() {			
			@Override
			public void handle(ActionEvent event) {
				isDeletedContry = true;
			}
		}); 

		addCountry.setOnAction(new EventHandler<ActionEvent>() {				
			@Override
			public void handle(ActionEvent event) {
				String tmp = getUserInput("Enter contry name");
				countriesChoiceBox.getItems().add(tmp);
				countriesChoiceBox.setValue(tmp);
			}
		});


		addMorePlayers.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				ComboBox<String> comboBox = new ComboBox<String>(options);
				playerList.add(comboBox);
				HBox newView = new HBox();
				Label strategyLabel =  new Label("Select Player "+(playerList.size())+" Strategy");
				strategyLabel.setPadding(new Insets(0,5,0,0));
				newView.getChildren().add(strategyLabel);
				newView.getChildren().add(comboBox);
				playerStratagiesContiner.getChildren().add(newView);
				//Set gap between children
				VBox.setMargin(newView, new Insets(8));

			}
		});	

		saveChanges.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if(continentChoiceBox.getValue().equals("Continents") && countriesChoiceBox.getValue().equals("Countries")){
					return ;
				}
				writeController.addData(editadjacentContries.getText(),continent,country,editContinentValue.getText(),isDeletedContinent,isDeletedContry);
				if(isDeletedContinent){    	  
					continentChoiceBox.getItems().remove(continent);
					continentChoiceBox.setValue("Continents");
				}
				if(isDeletedContry){
					countriesChoiceBox.getItems().remove(country);
					countriesChoiceBox.setValue("Countries");
				}
				isDeletedContinent = false; isDeletedContry = false;
			}
		});

		continentChoiceBox.setTooltip(new Tooltip("Select the Continent"));
		continentChoiceBox.getItems().add("Continents");
		continentChoiceBox.setValue("Continents");
		continentChoiceBox.getItems().addAll(continents);


		countriesChoiceBox.getItems().add("Countries");
		countriesChoiceBox.setValue("Countries");


		continentChoiceBox.valueProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue observable, String oldValue, String newValue){
				continent = newValue;
				ArrayList<String> territories = readController.getTerritoriesNames(continent);
				contries.clear();
				countriesChoiceBox.getItems().clear();
				if(territories != null){
					contries.addAll(territories);
				}
				country = "";
				editContinentValue.setText(""+readController.getContinentValue(continent));
				editadjacentContries.setText("");
				countriesChoiceBox.getItems().add("Countries");			    
				countriesChoiceBox.getItems().addAll(contries);
				countriesChoiceBox.setValue("Countries");
			}
		});


		countriesChoiceBox.valueProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue observable, String oldValue, String newValue) {
				if(newValue != null && !newValue.equals("Countries")){
					country = newValue;
					editadjacentContries.setText(readController.getAdjacentTerritories(continent, country).toString().replaceAll(" ", ""));
				}
			}
		});


		BorderPane borderPane = new BorderPane();

		Image backIcon = new Image(getClass().getResourceAsStream("/icons8-Back Arrow-35.png")); 
		getCloseButton().setGraphic(new ImageView(backIcon));
		//Adding to row 1 to UI grid
		gridPane.add(continentChoiceBox,0,0);
		gridPane.add(countriesChoiceBox,1,0);
		gridPane.add(editContinentValue, 2, 0);
		//Adding to row 2 to UI grid
		gridPane.add(editadjacentContries, 0, 1,3,1);
		//Adding to row 3 to UI grid
		gridPane.add(addContent, 0,2);
		gridPane.add(addCountry, 1,2);
		gridPane.add(deleteContent, 2,2);
		gridPane.add(deleteCountry, 3,2);
		
		addMorePlayers.setMinWidth(200);
		gridPane.add(addMorePlayers, 2, 3);
		
		//Adding 5th row to take Player strategies input
		gridPane.add(playerStratagiesContiner, 1, 4, 1, 4);

		
		
		ToolBar header = new ToolBar(closeButton);
		header.setStyle( 
				"-fx-border-style: solid inside;" + 
						"-fx-border-width: 0 0 1 0;" +  
				"-fx-border-color: black;");
		gridPane.setStyle("-fx-padding:10");
		borderPane.setTop(header);
		
		HBox footer = new HBox();
		footer.setStyle( 
				"-fx-border-style: solid inside;" + 
						"-fx-border-width: 1 0 0 0;" +
						"-fx-padding:10;"+
				"-fx-border-color: black;");
		gridPane.setStyle("-fx-padding:10");
		startGameButton.setText("Start Game");
		footer.setAlignment(Pos.CENTER_RIGHT);
		footer.setPadding(new Insets(15, 12, 15, 12));
		footer.getChildren().addAll(startGameButton,saveChanges);

		borderPane.setBottom(footer);
		borderPane.setCenter(gridPane);
		
		editorScene = new Scene(borderPane, 800, 600);
		
		return editorScene;		
	}



	/**
	 * @return the closeButton
	 */
	public Button getCloseButton() {
		return closeButton;
	}


	/**
	 * @return the startGameButton
	 */
	public Button getStartGameButton() {
		return startGameButton;
	}



	/**
	 * @return the numberOfPlayers given by user
	 */
	public int getNumberOfPlayers() {
		return playerList.size();
	}

	/**
	 * @return the players strategies given by user
	 */
	
	public String getPlayersStrategies() {
		String tmp = "";
		for(ComboBox<String> selectBox : playerList){
			tmp += ","+(selectBox.getValue()).toLowerCase().charAt(0);
		}
		return tmp;
	}

	/**
	 * @param question displays the question
	 * @return the input text given in dialog
	 */

	public String getUserInput(String question){
		dialog.setHeaderText(question);
		Optional<String> result = dialog.showAndWait();
		if (result.isPresent()){
			return result.get();
		}
		return null;
	}


}
