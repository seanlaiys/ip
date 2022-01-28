package alfred.exception;

public class InvalidInputException extends AlfredException {

    private static final String UNSURE = "☹ OOPS!!! I'm sorry, " +
            "but I don't know what that means :-(";

    public InvalidInputException() {
        super(UNSURE);
    }
}
