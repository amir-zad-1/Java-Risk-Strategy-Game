package model;

import java.util.Observable;

import util.LogMessageEnum;

/**
 * Controller sending print messages to View
 * <i>View here is just a console</i>
 * @author Amir
 */
public class Log extends Observable{

	/**
	 * a static log to send updates to Log View
	 */
	static Log logger = null;
	

	/**
	 * This method logs the message with a tag
	 * @param tag log message type
	 * @param message message content
	 */
	public static void log(LogMessageEnum tag, String message){
		if(logger != null)
			logger.sendNotify("\n"+tag + message);
	}

	/**
	 * This method logs the message with empty tag
	 * place a new log with INFO tag
	 * @param message message content
	 */
	public static void log(String message){
		if(logger != null)
			logger.sendNotify(message);
	}

	
	/**
     * Notify the observers with passed message
     * @param message is the message to be sent with notify
     */
    public void sendNotify(String message){    	
    		setChanged();
    		notifyObservers(message);
  
    }
    
    
    
    /**
     * @return {@link #loggerController}
     */
    public static Log getLogger() {
		return logger;
	}

    
	/**
	 * @param loggerController to initialize the {@value #loggerController}
	 */
	public static void setLogger(Log loggerController) {
		Log.logger = loggerController;
	}
	
		
}
