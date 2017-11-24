/**
 * 
 */
package view;

import java.awt.Label;
import java.util.Observable;
import java.util.Observer;

import controller.GameController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * @author SA
 *
 */
public class HumanPlayerView implements IView,Observer{

	
	/**
	 * label to ask question to Human Player
	 */
	Label questionLabel;
	
	GameController gameController = null;
	
	public HumanPlayerView(GameController new_gameController){
		gameController = new_gameController;
	}
	
	/**
	 * @return {@link Scene} object that holds all UI elements related to the HumanPlayerView
	 * @see view.IView#getView()
	 */
	@Override
	public Scene getView() {
		
		//initialize the questionLabel with text
		questionLabel  = new Label("Question for Human Player");
		//text filed to take input from user
		TextField playerInput = new TextField();
		
		//button to contact  saying user has given the input
		Button userAction = new Button("Submit");
		
		userAction.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				gameController.submitAnswer(playerInput.getText());
			}
		});	
		
		return null;
	}

	/** 
	 * Whenever the Human strategy needs the input it pushes the question to the User View
	 * 
	 */
	@Override
	public void update(Observable o, Object arg) {
		
		String question = (String) arg;
		questionLabel.setText(question);
		
	}

	
	
}
