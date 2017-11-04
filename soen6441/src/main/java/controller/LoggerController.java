package controller;

import util.LogMessageEnum;

/**
 * Controller sending print messages to View
 * <i>View here is just a console</i>
 * @author Amir
 */
public class LoggerController {


	/**
	 * @param tag log message type
	 * @param message message content
	 */
	public static void log(LogMessageEnum tag, String message){
		view.Logger.log(tag, message);
	}

	/**
	 * place a new log with INFO tag
	 * @param message message content
	 */
	public static void log(String message){
		view.Logger.log(message);
	}

}
