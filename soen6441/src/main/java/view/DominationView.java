package view;

import java.util.Observable;
import java.util.Observer;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * This class gives the view of percentage of map occupied by players
 * <p> implements Observer to provides a mechanism for receiving push-based notifications. 
 * @author SA
 */
public class DominationView implements Observer{

	/**
	 * Holds data with domination percentages
	 */
	Label dominationLabel = null;
	
	/** 
	 * return a {@link Scene} object which contains UI elements
	 */
	public Label getView() {
		
		dominationLabel = new Label("Domination View");		
        
		//set text color of label
		dominationLabel.setFont(new Font("Cambria", 16));
		dominationLabel.setTextFill(Color.web("#E91E63"));
		dominationLabel.setPadding(new Insets(10));		
		
		//set border
		dominationLabel.setStyle("-fx-padding: 5;" + "-fx-border-style: solid inside;"
	            + "-fx-border-width: 2;" + "-fx-border-insets: 5;"
	            + "-fx-border-radius: 5;" + "-fx-border-color: blue;");
		
		return dominationLabel;
	}

	/** 
	 * This method is called whenever the model calls {@link Observable#notifyObservers()} 
	 * We have to update the view only if pushed object is String instances 
	 * and belongs to DominationView
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(Observable model, Object object) {
		
		if(object instanceof String){			
			String updatedText = (String)object;
			if(updatedText.split(":")[0].equals("DominationView")){
				dominationLabel.setText(updatedText.split(":")[1]);
			}
		}
	}

}
