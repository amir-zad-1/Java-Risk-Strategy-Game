/**
 * 
 */
package view;

import java.io.File;
import java.util.ArrayList;

import controller.LoadMap;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener.Change;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.SingletonData;
import model.Territory;

/**
 * @author SA
 *
 */
public class MapEditorView {
	
	private Button closeButton;	
	private final VBox mainLayout = new VBox();
	private static Scene editorScene = null;
	
	
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
	    
	     Image imageOk = new Image(getClass().getResourceAsStream("/icons8-Back Arrow-35.png")); 
	     setCloseButton(new Button());
	     getCloseButton().setGraphic(new ImageView(imageOk));
	     gridPane.add(entries,0,0);
         HBox hb = new HBox();
         hb.getChildren().add(getCloseButton());
         hb.setStyle( 
                "-fx-border-style: solid inside;" + 
                "-fx-border-width: 0 0 1 0;" +  
                "-fx-border-color: black;");
         gridPane.setStyle("-fx-padding:10");
         HBox footer = new HBox();
         footer.getChildren().add(getCloseButton());
         footer.setStyle( 
                "-fx-border-style: solid inside;" + 
                "-fx-border-width: 1 0 0 0;" +  
                "-fx-border-color: black;");
         gridPane.setStyle("-fx-padding:10");
	     Button startGame = new Button();
	     startGame.setText("Start Game");
	     startGame.setOnAction(new EventHandler<ActionEvent>() {
	            @Override
	            public void handle(ActionEvent event){
	            	
	            }
	     });
	     footer.getChildren().add(startGame);
	     mainLayout.getChildren().addAll(hb,gridPane,footer);
	     editorScene = new Scene(mainLayout, 500, 500);
	     return editorScene;		
	}


	/**
	 * To set Close button
	 * @param button
	 */
	private void setCloseButton(Button button) {		
		this.closeButton = button;
	}


	/**
	 * @return the closeButton
	 */
	public Button getCloseButton() {
		return closeButton;
	}


	
}
