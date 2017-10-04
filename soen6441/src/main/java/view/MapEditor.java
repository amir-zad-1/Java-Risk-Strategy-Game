/**
 * 
 */
package view;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener.Change;
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
public class MapEditor {
	private final VBox mainLayout = new VBox();
	
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
	     scores.addListener((MapChangeListener.Change<? extends String, ? extends ArrayList<Territory>> change) -> {
	    	    boolean removed = change.wasRemoved();
	    	    if (removed != change.wasAdded()) {
	    	        if (removed) {
	    	            // no put for existing key
	    	            // remove pair completely
	    	            //entries.remove(new MapEntry<>(change.getKey(), (Shape) null));
	    	        } else {
	    	            // add new entry
	    	            //entries.add(new MapEntry<>(change.getKey(), change.getValueAdded()));
	    	        }
	    	    } else {
	    	        // replace existing entry
	    	        //MapEntry<String, Shape> entry = new MapEntry<>(change.getKey(), change.getValueAdded());

	    	        //int index = entries.indexOf(entry);
	    	        //entries.set(index, entry);
	    	    }
	     });
	    Image imageOk = new Image(getClass().getResourceAsStream("/icons8-Back Arrow-35.png")); 
	    Button closeButton = new Button();
	    closeButton.setGraphic(new ImageView(imageOk));
	    //gridPane.addRow(0, closeButton);
	    gridPane.add(entries,0,0);
        HBox hb = new HBox();
        hb.getChildren().add(closeButton);
        hb.setStyle( 
                "-fx-border-style: solid inside;" + 
                "-fx-border-width: 0 0 1 0;" +  
                "-fx-border-color: black;");
        gridPane.setStyle("-fx-padding:10");
	    mainLayout.getChildren().addAll(hb,gridPane);
		return new Scene(mainLayout, 500, 500);
		
	}	
}
