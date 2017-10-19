package util;

/**
 * Class used to log into the console
 * @author Amir
 */
public class Logger {

    /**
     * place a new log
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

}
