/**
 * 
 */
package bootstrap;

import java.util.Observable;
import java.util.Observer;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import view.IView;

/**
 * @author SA
 *
 */
public class DominationView implements Observer{

	/**
	 * Holds data regarding domination percentages
	 */
	Label dominationLabel = null;
	
	/** 
	 * return a {@link Scene} which contains UI elements
	 * @see view.IView#getView()
	 */
	public Label getView() {
		dominationLabel = new Label("Domination View");
		
		//set text color
		dominationLabel.setFont(new Font("Cambria", 13));
		dominationLabel.setTextFill(Color.web("#E91E63"));
		dominationLabel.setPadding(new Insets(10));
		
		return dominationLabel;
	}

	/** 
	 * Update the view only to String instances and check
	 * whether the pushed object belongs to DominationView
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(Observable model, Object object) {
		
		if(object instanceof String){
			String updatedText = (String)object;
			if(updatedText.split(":")[0].equals("DominationView")){
				dominationLabel.setText(updatedText);
			}
		}
	}

}
