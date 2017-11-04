/**
 * 
 */
package view;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 * Gives a player statistic view
 * @author SA
 */
public class PlayerStatisticsView {

	/**
	 * @return the playerBox
	 */
	public VBox getPlayerBox() {
		return playerBox;
	}
	/**
	 * @param playerBox the playerBox to set
	 */
	public void setPlayerBox(VBox playerBox) {
		this.playerBox = playerBox;
	}
	/**
	 * @return the actorName
	 */
	public Label getActorName() {
		return actorName;
	}
	/**
	 * @param actorName the actorName to set
	 */
	public void setActorName(Label actorName) {
		this.actorName = actorName;
	}
	/**
	 * @return the currentContry
	 */
	public Label getCurrentContry() {
		return currentContry;
	}
	/**
	 * @param currentContry the currentContry to set
	 */
	public void setCurrentContry(Label currentContry) {
		this.currentContry = currentContry;
	}
	/**
	 * @return the countriesWon
	 */
	public Label getCountriesWon() {
		return countriesWon;
	}
	/**
	 * @param countriesWon the countriesWon to set
	 */
	public void setCountriesWon(Label countriesWon) {
		this.countriesWon = countriesWon;
	}
	VBox playerBox = new VBox();
	Label actorName = new Label();
	Label currentContry = new Label();
	Label countriesWon = new Label();
	
	
	
}
