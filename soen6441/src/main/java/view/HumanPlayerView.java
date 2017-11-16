/**
 * 
 */
package view;

import java.util.Observable;
import java.util.Observer;
import java.util.Optional;

import controller.GameController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.HBox;

/**
 * This class is a Observer for HumanPlayer updates
 * Whenever Human player turn comes the update method will be called
 * @author SA
 */
public class HumanPlayerView implements Observer{

	
	/**
	 * label to ask question to Human Player
	 */
	TextInputDialog dialog = null;
	
	/**
	 * {@link GameController} is a controller to make state change through 
	 * controller in MVC architecture
	 */
	GameController gameController = null;
	
	
	/**
	 * Constructor to initialize the  {@link #gameController}
	 * @param new_gameController is the {@link GameController} is a controller to make state change through 
	 * controller in MVC architecture
	 */
	public HumanPlayerView(GameController new_gameController){
		gameController = new_gameController;
	}
	
	/**
	 * @return {@link HBox} object that holds all UI elements related to the HumanPlayerView
	 */
	public HBox getView() {
		
		//Horizontal box to hold UI elements 
		HBox container  = new HBox();
		
		dialog = new TextInputDialog();
			
		
		return container;
	}

	/** 
	 * Whenever the Human strategy needs the input it pushes the question to the User View
	 * {@link TextInputDialog} is used to ask the user input
	 */
	@Override
	public void update(Observable o, Object arg) {
	
		String question = (String) arg;
		dialog.setHeaderText(question);
		dialog.getEditor().clear();
		Optional<String> result = dialog.showAndWait();
		//pass the input given by the user to model through controller
		if (result.isPresent()){
			gameController.submitAnswer(result.get());
		}
		
	}

	
	
}
