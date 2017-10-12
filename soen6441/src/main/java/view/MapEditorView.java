/**
 * 
 */
package view;


import java.util.ArrayList;

import controller.ReadController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import model.Territory;

/**
 * @author Team15
 *
 */
public class MapEditorView {
	
	private Button closeButton;	
	private static Scene editorScene = null;
	ReadController readController = null;
	private static String country,continent = null; 
	private Button startGame = new Button();
	
	
	public MapEditorView(ReadController new_readController) {
		readController = new_readController;
	}



	public Scene getMapEditorView(){
		
		 ObservableList<String> continents = FXCollections.observableArrayList();		 
		 ObservableList<String> contries = FXCollections.observableArrayList();
		 
		 continents.addAll(model.SingletonData.continentValues.keySet());
		 GridPane gridPane = new GridPane();
		 gridPane.setHgap(10);
	     gridPane.setVgap(10);
	     
	     ChoiceBox<String> continentChoiceBox = new ChoiceBox<String>();
	     ChoiceBox<String> contriesChoiceBox = new ChoiceBox<String>();
	     
	     Button  deleteContent = new Button("Delete Continent");	     
	     Button deleteCountry = new Button("Delete Country");
	     
	     TextField editCountryName = new TextField ();
	     TextField editadjacentContries = new TextField ();
	     
	     editadjacentContries.setPrefWidth(800);
	     
	     continentChoiceBox.setTooltip(new Tooltip("Select the Continent"));
	     continentChoiceBox.getItems().add("Continents");
	     continentChoiceBox.setValue("Continents");
	     continentChoiceBox.getItems().addAll(continents);
	     continentChoiceBox.valueProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue observable, String oldValue, String newValue) {
				ArrayList<Territory> territories = model.SingletonData.continents.get(newValue);
				contries.clear();
				contriesChoiceBox.getItems().clear();
				for(Territory t:territories){
					contries.add(t.getTerritoryName());
				}
				continent = newValue;
				contriesChoiceBox.getItems().add("Countries");			    
				contriesChoiceBox.getItems().addAll(contries);
				contriesChoiceBox.setValue("Countries");
				
			}
		});

	     
	     contriesChoiceBox.valueProperty().addListener(new ChangeListener<String>() {
				@Override
				public void changed(ObservableValue observable, String oldValue, String newValue) {
					
					if(newValue != null && !newValue.equals("Countries")){
					editCountryName.setText(newValue);
					country = newValue;
					System.out.println(continent +country);
					editadjacentContries.setText(readController.getAdjacentTerritories(continent, country).toString());
					}
				}
	     });
	     
	     BorderPane borderPane = new BorderPane();

	     Image imageOk = new Image(getClass().getResourceAsStream("/icons8-Back Arrow-35.png")); 
	     closeButton = new Button();
	     getCloseButton().setGraphic(new ImageView(imageOk));
	     gridPane.add(continentChoiceBox,0,0);
	     gridPane.add(contriesChoiceBox,1,0);
	     gridPane.add(editCountryName, 2, 0);
	     gridPane.add(editadjacentContries, 0, 1,3,1);
	     gridPane.add(deleteContent, 0,2);
	     gridPane.add(deleteCountry, 1,2);
	     
	     
         ToolBar header = new ToolBar(closeButton);
         header.setStyle( 
                "-fx-border-style: solid inside;" + 
                "-fx-border-width: 0 0 1 0;" +  
                "-fx-border-color: black;");
         gridPane.setStyle("-fx-padding:10");
         borderPane.setTop(header);
         HBox footer = new HBox();
         
         
         
         
         footer.getChildren().add(getCloseButton());
         footer.setStyle( 
                "-fx-border-style: solid inside;" + 
                "-fx-border-width: 1 0 0 0;" +
                "-fx-padding:10;"+
                "-fx-border-color: black;");
         gridPane.setStyle("-fx-padding:10");
	     startGame = new Button();
	     startGame.setText("Start Game");
	     footer.setAlignment(Pos.CENTER_RIGHT);
	     footer.setPadding(new Insets(15, 12, 15, 12));
	     footer.getChildren().add(startGame);
	     borderPane.setBottom(footer);
	     borderPane.setCenter(gridPane);
	     editorScene = new Scene(borderPane, 500, 500);
	     return editorScene;		
	}



	/**
	 * @return the closeButton
	 */
	public Button getCloseButton() {
		return closeButton;
	}


	/**
	 * @return the startGame
	 */
	public Button getStartGame() {
		return startGame;
	}


	
}
