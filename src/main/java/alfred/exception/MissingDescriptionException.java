package alfred.exception;

public class MissingDescriptionException extends AlfredException {

    private static final String EMPTY = "I can't help you if you don't type anything ☹";

    public MissingDescriptionException() {
        super(EMPTY);
    }
}
