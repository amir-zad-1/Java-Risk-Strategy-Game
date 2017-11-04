
package bootstrap;

import controller.GameController;
import controller.RWMapFileController;
import controller.ReadController;
import controller.WriteController;

import model.DataReader;
import model.DataWriter;
import model.Notifier;
import view.WindowManager;

/**
 * @author Manohar Guntur
 * This class starts the game
 * This class gives connection between View, Controller, Model Objects.
 */
public class StartGame {

	/** 
	 * <li>Step 0: Initialize the controllers</li>
	 * <li>Step 1: Start the Welcome View</li>
	 * <li>Step 2: Inject controllers into Views through {@link WindowManager} </li>
	 * @param args from CMD
	 */
	public static void main(String[] args) {
		
		 //Creates Controller to controls read and write the .map file
		 RWMapFileController rwMapFileController = new RWMapFileController();
		 
		 //Creates controller, which is responsible to redirect Read operations
		 ReadController readController = new ReadController(new DataReader());
		 
		 //Creates controller, which is responsible to redirect Write operations
		 WriteController writeController = new WriteController(new DataWriter());
		 
		 //Create Notifier -A model class which sends updates to Views
		 Notifier notifier = new Notifier();
		 
		 GameController gameController = new GameController();
		 
		 
		 WindowManager.addRWControllers(rwMapFileController,readController,writeController,gameController);
		 javafx.application.Application.launch(WindowManager.class);
	}

}
