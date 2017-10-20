
package bootstrap;

import controller.GameController;
import controller.RWMapFileController;
import controller.ReadController;
import controller.WriteController;
import model.DataReader;
import model.DataWriter;
import view.WindowManager;

/**
 * @author Manohar Guntur
 * This class starts the game
 */
public class StartGame {

	/** 
	 * <li>Step 0: Initialize the controllers</li>
	 * <li>Step 1: Start the Welcome View</li>
	 * <li>Step 2: Inject controllers into Views through {@link WindowManager} </li>
	 * @param args from CMD
	 */
	public static void main(String[] args) {
		 RWMapFileController rwMapFileController = new RWMapFileController();
		 GameController gameController = new GameController();
		 ReadController readController = new ReadController(new DataReader());
		 WriteController writeController = new WriteController(new DataWriter());
		 WindowManager.addRWControllers(rwMapFileController,readController,writeController,gameController);
		 javafx.application.Application.launch(WindowManager.class);
	}

}
