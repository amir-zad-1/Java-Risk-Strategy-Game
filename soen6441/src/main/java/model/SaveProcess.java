/**
 * 
 */
package model;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * <p>
 * This class converts the state of the game into a file
 * and also deals with converting the saved game state file into model state such thet user can
 * resume the game where he stopped previously.
 * </p>
 * <i>To do to this this class uses JAVA Serialization</i>
 * @author SA
 */
public class SaveProcess {

	/**
	 * @param gameManager is instance {@link GameManager} we want to save
	 * @throws IOException if unable to save state of Object to a file
	 */
	public void saveState(GameManager gameManager) throws IOException{
		 FileOutputStream fos = new FileOutputStream("tempdata.tiger");
         ObjectOutputStream oos = new ObjectOutputStream(fos);
         oos.writeObject(gameManager);
         oos.close();
	}
	
}
