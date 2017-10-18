
package bootstrap;

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

	/**Step 0: Initialize the controllers
	 * Step 1: Start the Welcome View
	 * Step 2: Inject controllers into Views through {@link WindowManager}
	 * @param args from CMD
	 */
	public static void main(String[] args) {
		 RWMapFileController rwMapFileController = new RWMapFileController();
		 ReadController readController = new ReadController(new DataReader());
		 WriteController writeController = new WriteController(new DataWriter());
		 WindowManager.addRWControllers(rwMapFileController,readController,writeController);
		 javafx.application.Application.launch(WindowManager.class);
	}

}
