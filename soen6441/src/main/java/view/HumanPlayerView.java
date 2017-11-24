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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.HBox;

/**
 * @author SA
 *
 */
public class HumanPlayerView implements Observer{

	
	/**
	 * label to ask question to Human Player
	 */
	TextInputDialog dialog = null;
	
	GameController gameController = null;
	
	public HumanPlayerView(GameController new_gameController){
		gameController = new_gameController;
	}
	
	/**
	 * @return {@link Scene} object that holds all UI elements related to the HumanPlayerView
	 * @see view.IView#getView()
	 */
	public HBox getView() {
		
		HBox container  = new HBox();
		
		dialog = new TextInputDialog();
				
		//text filed to take input from user
		TextField playerInput = new TextField();
		container.getChildren().add(playerInput);
		
		//button to contact  saying user has given the input
		Button userAction = new Button("Submit");
		container.getChildren().add(userAction);
		
		userAction.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				gameController.submitAnswer(playerInput.getText());
			}
		});	
		
		return container;
	}

	/** 
	 * Whenever the Human strategy needs the input it pushes the question to the User View
	 * 
	 */
	@Override
	public void update(Observable o, Object arg) {
	
		String question = (String) arg;
		System.out.println("Ft "+question);
		dialog.setHeaderText(question);
		Optional<String> result = dialog.showAndWait();
		if (result.isPresent()){
			gameController.submitAnswer(result.get());
		}
		
	}

	
	
}
