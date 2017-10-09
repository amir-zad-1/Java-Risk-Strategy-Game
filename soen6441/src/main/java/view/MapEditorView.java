/**
 * 
 */
package view;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ToolBar;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import model.SingletonData;
import model.Territory;

/**
 * @author SA
 *
 */
public class MapEditorView {
	
	private Button closeButton;	
	private static Scene editorScene = null;
	
	private Button startGame = new Button();
	
	
	public Scene getMapEditorView(){
		 ObservableMap<String, ArrayList<Territory>> scores = FXCollections.observableHashMap();		 
		 scores.putAll(SingletonData.continents);
		 GridPane gridPane = new GridPane();
		 gridPane.setHgap(10);
	     gridPane.setVgap(10);
	     
	     ChoiceBox<String> entries = new ChoiceBox<String>();
	     
	     entries.setTooltip(new Tooltip("Select the Continent"));
	     entries.getItems().add("Continents");
	     entries.setValue("Continents");
	     entries.getItems().addAll(scores.keySet());
	     BorderPane borderPane = new BorderPane();

	     Image imageOk = new Image(getClass().getResourceAsStream("/icons8-Back Arrow-35.png")); 
	     closeButton = new Button();
	     getCloseButton().setGraphic(new ImageView(imageOk));
	     gridPane.add(entries,0,0);
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
                "-fx-padding:10"+
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
