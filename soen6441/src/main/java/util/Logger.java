package util;

public class Logger {

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

    public static void log(String message)
    {
        log(LogMessageEnum.INFO, message);
    }

}
