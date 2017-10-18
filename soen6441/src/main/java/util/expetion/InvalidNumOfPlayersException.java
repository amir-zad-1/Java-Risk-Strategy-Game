package util.expetion;

public class InvalidNumOfPlayersException extends BaseException  {

    public InvalidNumOfPlayersException(String message) {
        super(message);
    }

    public InvalidNumOfPlayersException()
    {
        this("There could be at minimum 2 and at maximum 6 players");
    }
}
