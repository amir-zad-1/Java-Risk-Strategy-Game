package util;

public class Logger {

    public static void log(LogMessageEnum tag, String message){
        String logTag = "";
        switch (tag)
        {
            case INFO:
                logTag = "[I] ";
                break;
            case WARNING:
                logTag = "[W] ";
                break;
            case ERROT:
                logTag = "[X] ";
                break;
        }
        System.out.println(logTag + message);
    }

    public static void log(String message)
    {
        log(LogMessageEnum.INFO, message);
    }

}
