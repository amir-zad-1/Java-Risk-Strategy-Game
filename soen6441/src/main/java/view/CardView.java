/**
 * 
 */
package view;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import model.Card;
import model.Player;

/**
 * @author m_guntur
 * This is card view which shows cards before reinforcement
 */
public class CardView implements Observer{

	
	/**
	 * {@link #cardholder} holds all cards belongs to player
	 */
	HBox cardholder; 
	
	
	/**
	 * Constructor to initialize the cardView
	 */
	public CardView() {

	}
	
	/**
	 * Gets called whenever the Observable pushed an update
	 * @param observable is the object that pushed the update
	 * @param arg is the extra arguments that passed
	 */
	@Override
	public void update(Observable observable, Object arg) {
		 
		 if(observable instanceof Player){
			  String[] params = arg.toString().split(":");
			  if(params[0].equals("CardView")){
				  cardholder.getChildren().clear();
				  Player player  = (Player) observable;
				  ArrayList<Card> cards = player.getCards();
				  for(Card card  :cards){
					  System.out.println(card.getCardName()+" Army value "+card.getCardValue());
					  Label card1 = new Label();
					  card1.setStyle("-fx-padding: 5;" + "-fx-border-style: solid inside;"
					            + "-fx-border-width: 2;" + "-fx-border-insets: 5;"
					            + "-fx-border-radius: 5;" + "-fx-border-color: blue;");
					  card1.setText(card.getCardName()+" Army value "+card.getCardValue());
					  cardholder.getChildren().add(card1);
				  }
				  if(cards.size() == 0){
					  Label card1 = new Label();
					  card1.setStyle("-fx-padding: 5;" + "-fx-border-style: solid inside;"
					            + "-fx-border-width: 2;" + "-fx-border-insets: 5;"
					            + "-fx-border-radius: 5;" + "-fx-border-color: blue;");
					  card1.setText("No Cards for "+player.getName());
					  cardholder.getChildren().add(card1);
				  }
			  }
		  }
		
	}
   
	/**
	 * @return {@link #cardholder}
	 */
	public HBox getCardholder() {
		return cardholder;
	}

	
	
	/**
	 * to initialize the Card View
	 */
	public void inti(){
		  cardholder = new HBox();
		  Label card1 = new Label();
		  card1.setStyle("-fx-padding: 5;" + "-fx-border-style: solid inside;"
		            + "-fx-border-width: 2;" + "-fx-border-insets: 5;"
		            + "-fx-border-radius: 5;" + "-fx-border-color: blue;");
		  card1.setText("No Cards for the player");
		  cardholder.getChildren().add(card1);
   }
	
	
}
