
package bootstrap;

import controller.GameController;
import controller.RWMapFileController;
import controller.ReadController;
import controller.WriteController;

import model.DataReader;
import model.DataWriter;
import model.Model;
import model.Notifier;
import view.IView;
import view.PhaseView;
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
		 
		 //Create Notifier: A model class which sends updates to Views
		 Model model = new Model();
		 Notifier notifier = new Notifier();		 
		 model.setNotifier(notifier);
		 
		 //Creates phaseView make it Observer
		 PhaseView phaseView = new PhaseView();
		 notifier.addObserver(phaseView);
		 
		 GameController gameController = new GameController();
		 
		 WindowManager.addControllers(rwMapFileController,readController,writeController,gameController);
		 WindowManager.setView(phaseView,"phaseview");
		 
		 javafx.application.Application.launch(WindowManager.class);
	}

}
