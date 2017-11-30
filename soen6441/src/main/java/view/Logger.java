package view;

import java.util.Observable;
import java.util.Observer;

import util.LogMessageEnum;

/**
 * @author m_guntur
 * Used to Log the messages on Command Prompt
 */
public class Logger implements Observer{
	 
	/**
     * place a new log on Command Prompt
     * @param tag log message type
     * @param message message content
     */
    public static void log(LogMessageEnum tag, String message){
        String logTag = "";
        switch (tag)
        {
            case INFO:
                logTag = "[:)] ";
                break;
            case WARNING:
                logTag = "[:|] ";
                break;
            case ERROT:
                logTag = "[:(] ";
                break;
        }
        System.out.println("");
        System.out.println(logTag + message);
    }

    /**
     * place a new log with INFO tag
     * @param message message content
     */
    public static void log(String message)
    {
        log(LogMessageEnum.INFO, message);
    }

	/**
	 * called whenever model pushes a message
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(Observable o, Object arg) {
		
		 System.out.println("");
	     System.out.println((String) arg);
		
	}

}
