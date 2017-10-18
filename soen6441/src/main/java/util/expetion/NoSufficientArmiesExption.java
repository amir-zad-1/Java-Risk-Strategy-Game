package util.expetion;

public class NoSufficientArmiesExption extends BaseException {

    public NoSufficientArmiesExption(String message) {
        super(message);
    }

    public NoSufficientArmiesExption() {
        this("There are no sufficient armies.");
    }
}